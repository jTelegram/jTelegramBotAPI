package com.jtelegram.api.events.payment;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.payments.ShippingQuery;

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
