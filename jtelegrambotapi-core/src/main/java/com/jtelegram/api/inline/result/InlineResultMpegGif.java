package com.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.result.framework.ThumbableInlineResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineResultMpegGif extends ThumbableInlineResult implements InlineResult.Urlable, InlineResult.Visual,
        InlineResult.Titled, InlineResult.Captioned, InlineResult.Duratable {
    private String title;
    private String caption;
    @SerializedName("mpeg4_duration")
    private Integer duration;
    @SerializedName("mpeg4_width")
    private Integer width;
    @SerializedName("mpeg4_height")
    private Integer height;
    @SerializedName("mpeg4_url")
    private String url;

    @Builder
    private InlineResultMpegGif(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl, String title, String caption, Integer duration, Integer width, Integer height, String url) {
        super(id, replyMarkup, inputMessageContent, thumbUrl);
        this.title = title;
        this.caption = caption;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.url = url;
    }
}
