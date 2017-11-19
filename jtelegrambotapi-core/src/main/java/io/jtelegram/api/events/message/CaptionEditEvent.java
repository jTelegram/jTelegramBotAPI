package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.CaptionableMessage;
import lombok.Getter;

public class CaptionEditEvent extends Event {
    @Getter
    private CaptionableMessage message;

    public CaptionEditEvent(TelegramBot bot, CaptionableMessage message) {
        super(bot);
        this.message = message;
    }
}
