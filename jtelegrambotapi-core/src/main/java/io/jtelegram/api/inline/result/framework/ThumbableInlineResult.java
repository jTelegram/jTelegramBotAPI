package io.jtelegram.api.inline.result.framework;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import lombok.Getter;

@Getter
public abstract class ThumbableInlineResult extends InlineResult {
    private String thumbUrl;

    protected ThumbableInlineResult(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl) {
        super(id, replyMarkup, inputMessageContent);
        this.thumbUrl = thumbUrl;
    }
}
