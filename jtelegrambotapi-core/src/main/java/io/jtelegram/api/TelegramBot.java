package io.jtelegram.api;

import lombok.Getter;

/**
 * @author Mazen Kotb
 */
@Getter
public class TelegramBot {
    private TelegramBotRegistry registry;
    private String apiKey;
    //private User info;

    public TelegramBot(TelegramBotRegistry registry, String apiKey) {
        this.registry = registry;
        this.apiKey = apiKey;
    }
}
