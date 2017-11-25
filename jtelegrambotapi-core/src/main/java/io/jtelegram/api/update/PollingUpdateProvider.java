package io.jtelegram.api.update;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.requests.webhooks.DeleteWebhook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PollingUpdateProvider implements UpdateProvider {
    private final Map<TelegramBot, Thread> botThreads = new ConcurrentHashMap<>();
    // THESE VALUES SHOULD NOT BE MODIFIED AFTER SET
    // DO NOT USE REFLECTION TO CHANGE THESE
    private int sleepInterval = 50;
    private int timeout = 10;
    private List<UpdateType> allowedUpdates = new ArrayList<>();

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
