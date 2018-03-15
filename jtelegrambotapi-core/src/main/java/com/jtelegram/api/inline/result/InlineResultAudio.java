package com.jtelegram.api.inline.result;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineResultAudio extends InlineResult implements InlineResult.Duratable, InlineResult.Captioned,
        InlineResult.Titled, InlineResult.Urlable {
    private String title;
    private String caption;
    private String performer;
    @SerializedName("audio_duration")
    private Integer duration;
    @SerializedName("audio_url")
    private String url;

    @Builder
    private InlineResultAudio(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String title, String caption, String performer, Integer duration, String url) {
        super(id, replyMarkup, inputMessageContent);
        this.title = title;
        this.caption = caption;
        this.performer = performer;
        this.duration = duration;
        this.url = url;
    }
}
