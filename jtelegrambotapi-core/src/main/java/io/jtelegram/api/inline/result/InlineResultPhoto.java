package io.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineResultPhoto extends ThumbableInlineResult implements InlineResult.Visual,
        InlineResult.Titled, InlineResult.Captioned, InlineResult.Describeable {
    private String caption;
    private String title;
    private String description;
    @SerializedName("photo_url")
    private String url;
    @SerializedName("photo_width")
    private Integer width;
    @SerializedName("photo_height")
    private Integer height;

    @Builder
    private InlineResultPhoto(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl,
                              String caption, String title, String description, String url, Integer width, Integer height) {
        super(id, replyMarkup, inputMessageContent, thumbUrl);
        this.caption = caption;
        this.title = title;
        this.description = description;
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
