package com.jtelegram.api.events.payment;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.payments.ShippingQuery;
import com.jtelegram.api.update.Update;
import lombok.ToString;

/**
 * When a new shipping query is received
 */
@ToString(callSuper = true)
public class ShippingQueryEvent extends UpdateEvent<Update.ShippingQueryUpdate> {
    private ShippingQuery shippingQuery;

    public ShippingQueryEvent(TelegramBot bot, Update.ShippingQueryUpdate update) {
        super(bot, update);
        this.shippingQuery = update.getShippingQuery();
    }
}
