package com.jtelegram.api.update;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.ex.handler.ErrorLogger;
import com.jtelegram.api.requests.webhooks.DeleteWebhook;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PollingUpdateProvider implements UpdateProvider {
    private final Map<TelegramBot, Thread> botThreads = new ConcurrentHashMap<>();
    // THESE VALUES SHOULD NOT BE MODIFIED AFTER SET
    // DO NOT USE REFLECTION TO CHANGE THESE
    @Builder.Default
    private int sleepInterval = 50;
    @Builder.Default
    private int timeout = 10;
    @Singular
    private List<UpdateType> allowedUpdates;
    @Builder.Default
    private Consumer<TelegramException> updateErrorHandler = ErrorLogger.builder()
            .identifier("Polling Update Provider")
            .build();

    @Override
    public void listenFor(TelegramBot bot) {
        // remove any previous webhooks in case
        // we are switching providers
        bot.perform(DeleteWebhook.builder()
                .callback(() -> startUpdateThread(bot))
                .errorHandler((e) -> startUpdateThread(bot))
                .build());
    }

    private void startUpdateThread(TelegramBot bot) {
        PollingUpdateRunnable runnable = new PollingUpdateRunnable(bot, this);
        Thread thread = new Thread(runnable, bot.getBotInfo().getUsername() + "-update-provider");
        thread.start();
        botThreads.put(bot, thread);
    }

    @Override
    public void stopListening(TelegramBot bot) {
        botThreads.remove(bot).interrupt();
    }
}
