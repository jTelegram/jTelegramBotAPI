package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.PhotoMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class PhotoMessageEvent extends MessageEvent<PhotoMessage> {
    public PhotoMessageEvent(TelegramBot bot, Update.MessageUpdate update, PhotoMessage message) {
        super(bot, update, message);
    }
}
