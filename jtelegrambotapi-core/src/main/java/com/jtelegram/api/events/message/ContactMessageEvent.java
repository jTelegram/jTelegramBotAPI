package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.ContactMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class ContactMessageEvent extends MessageEvent<ContactMessage> {
    public ContactMessageEvent(TelegramBot bot, Update.MessageUpdate update, ContactMessage message) {
        super(bot, update, message);
    }
}
