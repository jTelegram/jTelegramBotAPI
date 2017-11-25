package io.jtelegram.api.events.payment;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.MessageEvent;
import io.jtelegram.api.message.impl.SuccessfulPaymentMessage;
import io.jtelegram.api.message.payments.SuccessfulPayment;
import lombok.Getter;

@Getter
public class SuccessfulPaymentEvent extends MessageEvent<SuccessfulPaymentMessage> {
    private SuccessfulPayment payment;

    public SuccessfulPaymentEvent(TelegramBot bot, SuccessfulPaymentMessage message) {
        super(bot, message);
        this.payment = message.getSuccessfulPayment();
    }
}
