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
public class InlineCachedResultMpegGif extends InlineResult implements InlineResult.Cached,
        InlineResult.Titled, InlineResult.Captioned {
    @SerializedName("mpeg4_file_id")
    private IdInputFile fileId;
    private String title;
    private String caption;

    @Builder
    private InlineCachedResultMpegGif(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                                     IdInputFile fileId, String title, String caption) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
        this.title = title;
        this.caption = caption;
    }
}
