package io.jtelegram.api.inline.result;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.InlineResult;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
