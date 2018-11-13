package com.jtelegram.api.events.inline;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.inline.InlineQuery;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

/**
 * When an inline query is sent to the bot
 */
@Getter
@ToString(callSuper = true)
public class InlineQueryEvent extends UpdateEvent<Update.InlineQueryUpdate> {
    private InlineQuery query;

    public InlineQueryEvent(TelegramBot bot, Update.InlineQueryUpdate update) {
        super(bot, update);
        this.query = update.getInlineQuery();
    }
}
