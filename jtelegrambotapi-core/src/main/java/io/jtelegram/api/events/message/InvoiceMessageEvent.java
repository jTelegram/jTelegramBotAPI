package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.InvoiceMessage;

public class InvoiceMessageEvent extends MessageEvent<InvoiceMessage> {
    public InvoiceMessageEvent(TelegramBot bot, InvoiceMessage message) {
        super(bot, message);
    }
}
