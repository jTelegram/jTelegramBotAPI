package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.payments.SuccessfulPayment;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SuccessfulPaymentMessage extends Message<SuccessfulPayment> {
    private SuccessfulPayment successfulPayment;

    @Override
    public SuccessfulPayment getContent() {
        return successfulPayment;
    }
}
