package com.jtelegram.api.inline.result;

import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
// this class is the only one which doesn't
// allow for input message content, so it's
// handled like the outlier that it is.
public class InlineResultGame extends InlineResult {
    private String gameShortName;

    public InlineResultGame(String id, InlineKeyboardMarkup replyMarkup, String gameShortName) {
        super(id, replyMarkup, null);
        this.gameShortName = gameShortName;
    }

    @Override
    public InputMessageContent getInputMessageContent() {
        throw new UnsupportedOperationException();
    }
}
