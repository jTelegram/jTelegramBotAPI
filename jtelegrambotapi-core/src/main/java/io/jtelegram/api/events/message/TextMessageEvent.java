package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.events.Event;
import lombok.Getter;

public class TextMessageEvent extends Event {
    @Getter
    private final TextMessage message;

    public TextMessageEvent(TelegramBot bot, TextMessage message) {
        super(bot);
        this.message = message;
    }
}
