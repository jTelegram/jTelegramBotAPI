package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.payments.SuccessfulPayment;
import com.jtelegram.api.message.Message;
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
