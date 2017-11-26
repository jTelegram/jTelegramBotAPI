package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.games.Game;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameMessage extends Message<Game> {
    private Game game;

    @Override
    public Game getContent() {
        return game;
    }
}
