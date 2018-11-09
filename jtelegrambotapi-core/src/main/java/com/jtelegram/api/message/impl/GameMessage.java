package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.games.Game;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GameMessage extends Message<Game> {
    private Game game;

    @Override
    public Game getContent() {
        return game;
    }

    public EditTextMessage.EditTextMessageBuilder toEditTextRequest() {
        return EditTextMessage.forMessage(this);
    }
}
