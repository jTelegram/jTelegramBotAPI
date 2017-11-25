package io.jtelegram.api.requests.webhooks;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class GetWebhookInfo extends QueryTelegramRequest<WebhookInfo> {
    @Builder
    private GetWebhookInfo(Consumer<WebhookInfo> callback, Consumer<TelegramException> errorHandler) {
        super("getWebhookInfo", WebhookInfo.class, callback, errorHandler);
    }

    @Override
    protected boolean isValid() {
        return true;
    }
}
