package com.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineResultVoice extends InlineResult implements InlineResult.Urlable, InlineResult.Captioned,
        InlineResult.Titled, InlineResult.Duratable {
    private String title;
    private String caption;
    @SerializedName("voice_duration")
    private Integer duration;
    @SerializedName("voice_url")
    private String url;

    @Builder
    private InlineResultVoice(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                              String title, String caption, Integer duration, String url) {
        super(id, replyMarkup, inputMessageContent);
        this.title = title;
        this.caption = caption;
        this.duration = duration;
        this.url = url;
    }
}
