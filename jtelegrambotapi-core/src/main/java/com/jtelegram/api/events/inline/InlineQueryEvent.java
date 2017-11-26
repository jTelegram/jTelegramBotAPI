package com.jtelegram.api.events.inline;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.inline.InlineQuery;
import lombok.Getter;
import lombok.ToString;

/**
 * When an inline query is sent to the bot
 */
@Getter
@ToString
public class InlineQueryEvent extends Event {
    private InlineQuery query;

    public InlineQueryEvent(TelegramBot bot, InlineQuery query) {
        super(bot);
        this.query = query;
    }
}
