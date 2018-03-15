package com.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.result.framework.ThumbableInlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineResultVideo extends ThumbableInlineResult implements InlineResult.Titled, InlineResult.Duratable,
        InlineResult.Visual, InlineResult.Describeable, InlineResult.Captioned, InlineResult.Urlable {
    private String description;
    private String title;
    private String caption;
    private String mimeType;
    @SerializedName("video_url")
    private String url;
    @SerializedName("video_width")
    private Integer width;
    @SerializedName("video_height")
    private Integer height;
    @SerializedName("video_duration")
    private Integer duration;

    @Builder
    private InlineResultVideo(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl,
                              String decription, String title, String caption, String mimeType, String url, Integer width, Integer height,
                              Integer duration) {
        super(id, replyMarkup, inputMessageContent, thumbUrl);
        this.description = decription;
        this.title = title;
        this.caption = caption;
        this.mimeType = mimeType;
        this.url = url;
        this.width = width;
        this.height = height;
        this.duration = duration;
    }
}
