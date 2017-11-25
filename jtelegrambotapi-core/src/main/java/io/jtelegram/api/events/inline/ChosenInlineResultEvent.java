package io.jtelegram.api.events.inline;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.inline.result.ChosenInlineResult;
import lombok.Getter;
import lombok.ToString;

/**
 * When a user has selected their inline result
 */
@Getter
@ToString
public class ChosenInlineResultEvent extends Event {
    private ChosenInlineResult chosenResult;

    public ChosenInlineResultEvent(TelegramBot bot, ChosenInlineResult chosenResult) {
        super(bot);
        this.chosenResult = chosenResult;
    }
}
