package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.InvoiceMessage;
import com.jtelegram.api.update.Update;

public class InvoiceMessageEvent extends MessageEvent<InvoiceMessage> {
    public InvoiceMessageEvent(TelegramBot bot, Update.MessageUpdate update, InvoiceMessage message) {
        super(bot, update, message);
    }
}
