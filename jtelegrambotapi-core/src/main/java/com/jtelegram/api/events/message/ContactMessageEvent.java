package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.ContactMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class ContactMessageEvent extends MessageEvent<ContactMessage> {
    public ContactMessageEvent(TelegramBot bot, ContactMessage message) {
        super(bot, message);
    }
}
