package com.jtelegram.api.events.inline.keyboard;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class CallbackQueryEvent extends UpdateEvent<Update.CallbackQueryUpdate> {
    private CallbackQuery query;

    public CallbackQueryEvent(TelegramBot bot, Update.CallbackQueryUpdate update) {
        super(bot, update);
        this.query = update.getCallbackQuery();
    }
}
