package com.jtelegram.api.events.message.edit;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
public class CaptionEditEvent extends UpdateEvent<Update.EditedMessageUpdate> {
    @Getter
    private CaptionableMessage message;

    public CaptionEditEvent(TelegramBot bot, Update.EditedMessageUpdate update, CaptionableMessage message) {
        super(bot, update);
        this.message = message;
    }
}
