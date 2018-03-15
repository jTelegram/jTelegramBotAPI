package com.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.DimensionalThumbableInlineResult;
import com.jtelegram.api.inline.result.framework.InlineResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineResultDocument extends DimensionalThumbableInlineResult implements InlineResult.Titled,
        InlineResult.Captioned, InlineResult.Describeable, InlineResult.Urlable {
    private String title;
    private String caption;
    private String description;
    private String mimeType;
    @SerializedName("document_url")
    private String url;

    @Builder
    private InlineResultDocument(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl,
                                 Integer thumbWidth, Integer thumbHeight, String title, String caption, String description, String mimeType, String url) {
        super(id, replyMarkup, inputMessageContent, thumbUrl, thumbWidth, thumbHeight);
        this.title = title;
        this.caption = caption;
        this.description = description;
        this.mimeType = mimeType;
        this.url = url;
    }
}
