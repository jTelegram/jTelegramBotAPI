package com.jtelegram.api.events.message.edit;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.CaptionableMessage;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
public class CaptionEditEvent extends Event {
    @Getter
    private CaptionableMessage message;

    public CaptionEditEvent(TelegramBot bot, CaptionableMessage message) {
        super(bot);
        this.message = message;
    }
}
