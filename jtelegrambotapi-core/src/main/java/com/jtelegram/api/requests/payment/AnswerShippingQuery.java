package com.jtelegram.api.requests.payment;

import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.payments.ShippingOption;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class AnswerShippingQuery extends UpdateTelegramRequest {
    private String shippingQueryId;
    private Boolean ok;
    private List<ShippingOption> shippingOptions;
    private String errorMessage;

    @Builder
    private AnswerShippingQuery(Consumer<TelegramException> errorHandler, Runnable callback, String shippingQueryId, Boolean ok, List<ShippingOption> shippingOptions, String errorMessage) {
        super("answerShippingQuery", errorHandler, callback);
        this.shippingQueryId = shippingQueryId;
        this.ok = ok;
        this.shippingOptions = shippingOptions;
        this.errorMessage = errorMessage;
    }

    public static class AnswerShippingQueryBuilder {
        public AnswerShippingQueryBuilder addShippingOption(ShippingOption option) {
            if (shippingOptions == null) {
                shippingOptions = new ArrayList<>();
            }

            shippingOptions.add(option);
            return this;
        }

        public AnswerShippingQueryBuilder addShippingOptions(Collection<ShippingOption> options) {
            if (shippingOptions == null) {
                shippingOptions = new ArrayList<>();
            }

            shippingOptions.addAll(options);
            return this;
        }
    }

    @Override
    protected boolean isValid() {
        return shippingQueryId != null && ok != null && (ok || errorMessage != null);
    }
}
