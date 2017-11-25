package io.jtelegram.api.requests.webhooks;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
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
