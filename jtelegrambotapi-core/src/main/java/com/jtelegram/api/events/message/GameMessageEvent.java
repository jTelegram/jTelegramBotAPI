package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.GameMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class GameMessageEvent extends MessageEvent<GameMessage> {
    public GameMessageEvent(TelegramBot bot, Update.MessageUpdate update, GameMessage message) {
        super(bot, update, message);
    }
}
