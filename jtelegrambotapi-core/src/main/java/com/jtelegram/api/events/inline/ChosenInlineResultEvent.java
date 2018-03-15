package com.jtelegram.api.events.inline;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import lombok.Getter;
import lombok.ToString;

/**
 * When a user has selected their inline result
 */
@Getter
@ToString(callSuper = true)
public class ChosenInlineResultEvent extends Event {
    private ChosenInlineResult chosenResult;

    public ChosenInlineResultEvent(TelegramBot bot, ChosenInlineResult chosenResult) {
        super(bot);
        this.chosenResult = chosenResult;
    }
}
