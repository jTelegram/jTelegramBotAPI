package io.jtelegram.api.requests.payment;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class AnswerPreCheckoutQuery extends UpdateTelegramRequest {
    private String preCheckoutQueryId;
    private Boolean ok;
    private String errorMessage;

    @Builder
    private AnswerPreCheckoutQuery(Consumer<TelegramException> errorHandler, Runnable callback, String preCheckoutQueryId, Boolean ok, String errorMessage) {
        super("answerPreCheckoutQuery", errorHandler, callback);
        this.preCheckoutQueryId = preCheckoutQueryId;
        this.ok = ok;
        this.errorMessage = errorMessage;
    }

    @Override
    protected boolean isValid() {
        return preCheckoutQueryId != null && ok != null && (ok || errorMessage != null);
    }
}
