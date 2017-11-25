package io.jtelegram.api.update;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.requests.GetUpdates;
import io.jtelegram.api.requests.framework.TelegramRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
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
            handleUpdate(bot, UpdateType.from(update.getClass()), update);
        }

        offset = Stream.of(updates).mapToInt(Update::getUpdateId).max().orElse(offset - 1) + 1;
    }

    public static <T extends Update> void handleUpdate(TelegramBot bot, UpdateType<T> type, Update update) {
        if (type != null) {
            Class<T> clazz = type.getUpdateClass();

            bot.getEventRegistry().dispatch(type.getEventFunction().apply(bot, clazz.cast(update)));
        }
    }
}
