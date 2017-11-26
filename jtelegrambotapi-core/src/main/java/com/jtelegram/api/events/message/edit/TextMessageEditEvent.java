package com.jtelegram.api.events.message.edit;

import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import lombok.Getter;

public class TextMessageEditEvent extends Event {
    @Getter
    private TextMessage newMessage;

    public TextMessageEditEvent(TelegramBot bot, TextMessage newMessage) {
        super(bot);
        this.newMessage = newMessage;
    }
}
