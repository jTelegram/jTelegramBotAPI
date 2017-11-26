package com.jtelegram.api.inline.result.cached;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.inline.input.InputMessageContent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineCachedResultAudio extends InlineResult implements InlineResult.Cached, InlineResult.Captioned {
    @SerializedName("audio_file_id")
    private IdInputFile fileId;
    private String caption;

    @Builder
    private InlineCachedResultAudio(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                                    IdInputFile fileId, String caption) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
        this.caption = caption;
    }
}
