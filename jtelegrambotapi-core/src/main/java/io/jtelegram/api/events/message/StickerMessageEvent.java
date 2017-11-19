package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.StickerMessage;

public class StickerMessageEvent extends MessageEvent<StickerMessage> {
    public StickerMessageEvent(TelegramBot bot, StickerMessage message) {
        super(bot, message);
    }
}
