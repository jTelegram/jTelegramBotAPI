package com.jtelegram.api.events.payment;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.payments.PreCheckoutQuery;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PreCheckoutQueryEvent extends Event {
    private PreCheckoutQuery checkoutQuery;

    public PreCheckoutQueryEvent(TelegramBot bot, PreCheckoutQuery checkoutQuery) {
        super(bot);
        this.checkoutQuery = checkoutQuery;
    }
}
