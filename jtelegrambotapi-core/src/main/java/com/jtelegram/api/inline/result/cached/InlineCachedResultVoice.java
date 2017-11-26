package com.jtelegram.api.inline.result.cached;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.message.input.file.IdInputFile;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineCachedResultVoice extends InlineResult implements InlineResult.Cached,
        InlineResult.Titled, InlineResult.Captioned {
    @SerializedName("voice_file_id")
    private IdInputFile fileId;
    private String title;
    private String caption;

    @Builder
    private InlineCachedResultVoice(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                                    IdInputFile fileId, String title, String caption) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
        this.title = title;
        this.caption = caption;
    }
}

