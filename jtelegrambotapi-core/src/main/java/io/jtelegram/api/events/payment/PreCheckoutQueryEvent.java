package io.jtelegram.api.events.payment;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.payments.PreCheckoutQuery;
import lombok.Getter;

@Getter
public class PreCheckoutQueryEvent extends Event {
    private PreCheckoutQuery checkoutQuery;

    public PreCheckoutQueryEvent(TelegramBot bot, PreCheckoutQuery checkoutQuery) {
        super(bot);
        this.checkoutQuery = checkoutQuery;
    }
}
