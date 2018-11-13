package com.jtelegram.api.events.payment;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.payments.PreCheckoutQuery;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PreCheckoutQueryEvent extends UpdateEvent<Update.PreCheckoutQueryUpdate> {
    private PreCheckoutQuery checkoutQuery;

    public PreCheckoutQueryEvent(TelegramBot bot, Update.PreCheckoutQueryUpdate update) {
        super(bot, update);
        this.checkoutQuery = update.getPreCheckoutQuery();
    }
}
