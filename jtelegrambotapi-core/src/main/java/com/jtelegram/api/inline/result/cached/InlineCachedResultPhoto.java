package com.jtelegram.api.inline.result.cached;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InlineCachedResultPhoto extends InlineResult implements InlineResult.Cached, InlineResult.Titled,
        InlineResult.Describeable, InlineResult.Captioned {
    @SerializedName("photo_file_id")
    private IdInputFile fileId;
    private String description;
    private String caption;
    private String title;

    @Builder
    private InlineCachedResultPhoto(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                                   IdInputFile fileId, String description, String caption, String title) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
        this.description = description;
        this.caption = caption;
        this.title = title;
    }
}
