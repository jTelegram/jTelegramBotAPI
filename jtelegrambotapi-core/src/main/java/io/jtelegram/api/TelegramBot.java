package io.jtelegram.api;

import io.jtelegram.api.requests.GetMe;
import io.jtelegram.api.requests.framework.BotRequest;
import io.jtelegram.api.requests.framework.BotRequestQueue;
import io.jtelegram.api.requests.framework.TelegramRequest;
import io.jtelegram.api.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"apiKey", "botInfo"})
public class TelegramBot {
    private final BotRequestQueue requestQueue;
    private TelegramBotRegistry registry;
    private String apiKey;
    @Setter
    private User botInfo;

    TelegramBot(TelegramBotRegistry registry, String apiKey) {
        this.registry = registry;
        this.apiKey = apiKey;
        this.requestQueue = new BotRequestQueue(registry.getClient());

        requestQueue.start();
    }

    public void perform(TelegramRequest request) {
        requestQueue.getRequestQueue().add(new BotRequest(this, request));
    }
}
