package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.TextMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class TextMessageEvent extends MessageEvent<TextMessage> {
    public TextMessageEvent(TelegramBot bot, TextMessage message) {
        super(bot, message);
    }
}
