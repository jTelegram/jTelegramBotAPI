package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.ContactMessage;

public class ContactMessageEvent extends MessageEvent<ContactMessage> {
    public ContactMessageEvent(TelegramBot bot, ContactMessage message) {
        super(bot, message);
    }
}
