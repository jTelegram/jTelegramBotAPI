package com.jtelegram.api.requests.webhooks;

import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class DeleteWebhook extends UpdateTelegramRequest {
    @Builder
    private DeleteWebhook(Consumer<TelegramException> errorHandler, Runnable callback) {
        super("deleteWebhook", errorHandler, callback);
    }

    @Override
    protected boolean isValid() {
        return true;
    }
}
