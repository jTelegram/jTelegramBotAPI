package io.jtelegram.api.inline.result.cached;

import com.google.gson.annotations.SerializedName;
import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.InlineResult;
import io.jtelegram.api.message.input.file.IdInputFile;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InlineCachedResultSticker extends InlineResult implements InlineResult.Cached {
    @SerializedName("sticker_file_id")
    private IdInputFile fileId;

    @Builder
    private InlineCachedResultSticker(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, IdInputFile fileId) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
    }
}
