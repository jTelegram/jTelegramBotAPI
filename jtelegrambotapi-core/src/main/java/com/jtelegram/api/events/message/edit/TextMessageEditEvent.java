package com.jtelegram.api.events.message.edit;

import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
public class TextMessageEditEvent extends UpdateEvent<Update.EditedMessageUpdate> {
    @Getter
    private TextMessage newMessage;

    public TextMessageEditEvent(TelegramBot bot, Update.EditedMessageUpdate update, TextMessage newMessage) {
        super(bot, update);
        this.newMessage = newMessage;
    }
}
