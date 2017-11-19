package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.Message;
import lombok.Getter;

public class MessageEvent<T extends Message> extends Event {
    @Getter
    private T message;

    public MessageEvent(TelegramBot bot, T message) {
        super(bot);
        this.message = message;
    }
}
