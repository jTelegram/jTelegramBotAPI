package io.jtelegram.api.inline.result.framework;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import lombok.Getter;

@Getter
public abstract class DimensionalThumbableInlineResult extends ThumbableInlineResult {
    private Integer thumbWidth;
    private Integer thumbHeight;

    protected DimensionalThumbableInlineResult(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl, Integer thumbWidth, Integer thumbHeight) {
        super(id, replyMarkup, inputMessageContent, thumbUrl);
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }
}
