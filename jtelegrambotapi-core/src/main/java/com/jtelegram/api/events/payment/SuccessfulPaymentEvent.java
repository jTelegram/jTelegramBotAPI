package com.jtelegram.api.events.payment;

import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.message.impl.SuccessfulPaymentMessage;
import com.jtelegram.api.message.payments.SuccessfulPayment;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SuccessfulPaymentEvent extends MessageEvent<SuccessfulPaymentMessage> {
    private SuccessfulPayment payment;

    public SuccessfulPaymentEvent(TelegramBot bot, Update.MessageUpdate update, SuccessfulPaymentMessage message) {
        super(bot, update, message);
        this.payment = message.getSuccessfulPayment();
    }
}
