package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.InvoiceMessage;

public class InvoiceMessageEvent extends MessageEvent<InvoiceMessage> {
    public InvoiceMessageEvent(TelegramBot bot, InvoiceMessage message) {
        super(bot, message);
    }
}
