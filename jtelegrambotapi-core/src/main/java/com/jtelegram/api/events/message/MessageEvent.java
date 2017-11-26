package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.Message;
import lombok.Getter;
import lombok.ToString;

@ToString
public class MessageEvent<T extends Message> extends Event {
    @Getter
    private T message;

    public MessageEvent(TelegramBot bot, T message) {
        super(bot);
        this.message = message;
    }
}
