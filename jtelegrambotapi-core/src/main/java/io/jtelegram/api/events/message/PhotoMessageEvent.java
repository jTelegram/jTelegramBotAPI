package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.PhotoMessage;

public class PhotoMessageEvent extends MessageEvent<PhotoMessage> {
    public PhotoMessageEvent(TelegramBot bot, PhotoMessage message) {
        super(bot, message);
    }
}
