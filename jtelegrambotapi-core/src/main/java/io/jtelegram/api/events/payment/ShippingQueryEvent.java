package io.jtelegram.api.events.payment;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.payments.ShippingQuery;

/**
 * When a new shipping query is received
 */
public class ShippingQueryEvent extends Event {
    private ShippingQuery shippingQuery;

    public ShippingQueryEvent(TelegramBot bot, ShippingQuery shippingQuery) {
        super(bot);
        this.shippingQuery = shippingQuery;
    }
}
