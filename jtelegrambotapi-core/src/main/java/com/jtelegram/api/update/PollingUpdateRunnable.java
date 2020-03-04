package com.jtelegram.api.update;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.NetworkException;
import com.jtelegram.api.requests.GetUpdates;
import com.jtelegram.api.requests.framework.TelegramRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;
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
                    .errorHandler((error) -> {
                        if (error instanceof NetworkException) {
                            // this exception is expected for polling
                            if (((NetworkException) error).getUnderlyingException() instanceof SocketTimeoutException) {
                                return;
                            }
                        }

                        owner.getUpdateErrorHandler().accept(error);
                    })
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
            if (update != null) {
                handleUpdate(bot, UpdateType.from(update.getClass()), update);
            }
        }

        offset = Stream.of(updates).filter(Objects::nonNull).mapToInt(Update::getUpdateId).max().orElse(offset - 1) + 1;
    }

    public static <T extends Update> void handleUpdate(TelegramBot bot, UpdateType<T> type, Update update) {
        if (type != null) {
            Class<T> clazz = type.getUpdateClass();

            bot.getEventRegistry().dispatch(type.getEventFunction().apply(bot, clazz.cast(update)));
        }
    }
}
