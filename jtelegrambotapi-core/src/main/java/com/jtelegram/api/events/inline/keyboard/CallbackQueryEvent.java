package com.jtelegram.api.events.inline.keyboard;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.inline.CallbackQuery;
import lombok.Getter;

@Getter
public class CallbackQueryEvent extends Event {
    private CallbackQuery query;

    public CallbackQueryEvent(TelegramBot bot, CallbackQuery query) {
        super(bot);
        this.query = query;
    }
}
