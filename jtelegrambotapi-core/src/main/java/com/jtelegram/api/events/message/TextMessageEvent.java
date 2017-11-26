package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.TelegramBot;
import lombok.ToString;

@ToString(callSuper = true)
public class TextMessageEvent extends MessageEvent<TextMessage> {
    public TextMessageEvent(TelegramBot bot, TextMessage message) {
        super(bot, message);
    }
}
