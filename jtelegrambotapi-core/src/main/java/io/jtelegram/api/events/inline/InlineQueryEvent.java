package io.jtelegram.api.events.inline;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.inline.InlineQuery;
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
