package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.PhotoMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class PhotoMessageEvent extends MessageEvent<PhotoMessage> {
    public PhotoMessageEvent(TelegramBot bot, PhotoMessage message) {
        super(bot, message);
    }
}
