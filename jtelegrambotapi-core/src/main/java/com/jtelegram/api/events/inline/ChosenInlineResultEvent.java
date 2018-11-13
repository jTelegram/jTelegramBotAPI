package com.jtelegram.api.events.inline;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

/**
 * When a user has selected their inline result
 */
@Getter
@ToString(callSuper = true)
public class ChosenInlineResultEvent extends UpdateEvent<Update.ChosenInlineResultUpdate> {
    private ChosenInlineResult chosenResult;

    public ChosenInlineResultEvent(TelegramBot bot, Update.ChosenInlineResultUpdate update) {
        super(bot, update);
        this.chosenResult = update.getChosenInlineResult();
    }
}
