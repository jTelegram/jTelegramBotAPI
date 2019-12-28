package com.jtelegram.api.update;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.requests.GetUpdates;
import com.jtelegram.api.requests.framework.TelegramRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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
                HttpResponse<String>
                        response = bot.getRegistry().getClient().send(request.build(bot).build(),
                                                                      HttpResponse.BodyHandlers.ofString(
                                                                              StandardCharsets.UTF_8));

                request.handleResponse(response);
            } catch (IOException e) {
                request.handleException(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
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
