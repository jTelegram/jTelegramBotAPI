package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class TextMessageEvent extends MessageEvent<TextMessage> {
    public TextMessageEvent(TelegramBot bot, Update.MessageUpdate update, TextMessage message) {
        super(bot, update, message);
    }
}
