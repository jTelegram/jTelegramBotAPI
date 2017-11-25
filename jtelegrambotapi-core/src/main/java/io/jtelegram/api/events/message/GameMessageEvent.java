package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.GameMessage;

public class GameMessageEvent extends MessageEvent<GameMessage> {
    public GameMessageEvent(TelegramBot bot, GameMessage message) {
        super(bot, message);
    }
}
