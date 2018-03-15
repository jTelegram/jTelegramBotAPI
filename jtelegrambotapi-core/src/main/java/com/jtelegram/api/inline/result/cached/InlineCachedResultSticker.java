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
@ToString(callSuper = true)
public class InlineCachedResultSticker extends InlineResult implements InlineResult.Cached {
    @SerializedName("sticker_file_id")
    private IdInputFile fileId;

    @Builder
    private InlineCachedResultSticker(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, IdInputFile fileId) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
    }
}
