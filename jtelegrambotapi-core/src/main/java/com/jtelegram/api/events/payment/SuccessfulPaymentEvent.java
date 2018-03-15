package com.jtelegram.api.events.payment;

import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.message.impl.SuccessfulPaymentMessage;
import com.jtelegram.api.message.payments.SuccessfulPayment;
import com.jtelegram.api.TelegramBot;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SuccessfulPaymentEvent extends MessageEvent<SuccessfulPaymentMessage> {
    private SuccessfulPayment payment;

    public SuccessfulPaymentEvent(TelegramBot bot, SuccessfulPaymentMessage message) {
        super(bot, message);
        this.payment = message.getSuccessfulPayment();
    }
}
