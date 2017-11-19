package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.impl.TextMessage;
import lombok.Getter;

public class TextMessageEditEvent extends Event {
    @Getter
    private TextMessage newMessage;

    public TextMessageEditEvent(TelegramBot bot, TextMessage newMessage) {
        super(bot);
        this.newMessage = newMessage;
    }
}
