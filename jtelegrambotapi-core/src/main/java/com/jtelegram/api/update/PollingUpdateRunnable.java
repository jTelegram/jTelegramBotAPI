package com.jtelegram.api.update;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.requests.GetUpdates;
import com.jtelegram.api.requests.framework.TelegramRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PollingUpdateRunnable implements Runnable {
    private int offset = 0;
    private final TelegramBot bot;
    private final PollingUpdateProvider owner;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            TelegramRequest request = GetUpdates.builder()
                    .allowedUpdates(owner.getAllowedUpdates())
                    .offset(offset)
                    .timeout(owner.getTimeout())
                    .callback(this::handleUpdates)
                    .errorHandler(owner.getUpdateErrorHandler())
                    .build();

            try {
                Response response = bot.getRegistry().getClient().newCall(request.build(bot).build()).execute();

                request.handleResponse(response);
            } catch (IOException e) {
                request.handleException(e);
            }

            try {
                Thread.sleep(owner.getSleepInterval());
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    public void handleUpdates(Update[] updates) {
        for (Update update : updates) {
            handleUpdate(bot, owner.getMaxUpdateAge(), UpdateType.from(update.getClass()), update);
        }

        offset = Stream.of(updates).mapToInt(Update::getUpdateId).max().orElse(offset - 1) + 1;
    }

    // legacy method for mismatched core + webhooks
    public static <T extends Update> void handleUpdate(TelegramBot bot, UpdateType<T> type, Update update) {
        handleUpdate(bot, -1L, type, update);
    }

    public static <T extends Update> void handleUpdate(TelegramBot bot, long maxAge, UpdateType<T> type, Update update) {
        if (type != null) {
            Class<T> clazz = type.getUpdateClass();

            if (isTooOld(update, maxAge)) {
                return;
            }

            bot.getEventRegistry().dispatch(type.getEventFunction().apply(bot, clazz.cast(update)));
        }
    }

    private static boolean isTooOld(Update update, long maxDistance) {
        if (maxDistance <= 0) {
            return false;
        }

        if (!(update instanceof Update.TimeSensitiveUpdate)) {
            return false;
        }

        long eventTime = ((Update.TimeSensitiveUpdate) update).getEventTime();
        long currentUnixTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        long timeDistance = Math.max(0, currentUnixTime - eventTime);

        return timeDistance >= maxDistance;
    }
}
