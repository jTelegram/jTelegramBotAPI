package io.jtelegram.api.events.inline.keyboard;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.inline.CallbackQuery;
import lombok.Getter;

@Getter
public class CallbackQueryEvent extends Event {
    private CallbackQuery query;

    public CallbackQueryEvent(TelegramBot bot, CallbackQuery query) {
        super(bot);
        this.query = query;
    }
}
