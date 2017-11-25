package io.jtelegram.api.inline.result;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineResultArticle extends DimensionalThumbableInlineResult implements InlineResult.Titled, InlineResult.Urlable, InlineResult.Describeable {
    private String title;
    private String url;
    private Boolean hideUrl;
    private String description;

    @Builder
    private InlineResultArticle(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl, Integer thumbWidth, Integer thumbHeight, String title, String url, Boolean hideUrl, String description) {
        super(id, replyMarkup, inputMessageContent, thumbUrl, thumbWidth, thumbHeight);
        this.title = title;
        this.url = url;
        this.hideUrl = hideUrl;
        this.description = description;
    }
}
