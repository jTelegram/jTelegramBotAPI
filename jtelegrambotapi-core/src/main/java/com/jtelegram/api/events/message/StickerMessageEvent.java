package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.StickerMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class StickerMessageEvent extends MessageEvent<StickerMessage> {
    public StickerMessageEvent(TelegramBot bot, StickerMessage message) {
        super(bot, message);
    }
}
