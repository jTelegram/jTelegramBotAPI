package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
public class MessageEvent<T extends Message> extends UpdateEvent<Update.MessageUpdate> {
    @Getter
    private T message;

    public MessageEvent(TelegramBot bot, Update.MessageUpdate update, T message) {
        super(bot, update);
        this.message = message;
    }
}
