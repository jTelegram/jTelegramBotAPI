package com.jtelegram.api.inline.result.cached;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.inline.input.InputMessageContent;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineCachedResultMpegGif extends InlineResult implements InlineResult.Cached,
        InlineResult.Titled, InlineResult.Captioned {
    @SerializedName("mpeg4_file_id")
    private IdInputFile fileId;
    private String title;
    private String caption;
    private ParseMode captionParseMode;

    @Builder
    private InlineCachedResultMpegGif(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                                      IdInputFile fileId, String title, String caption, ParseMode captionParseMode) {
        super(id, replyMarkup, inputMessageContent);
        this.fileId = fileId;
        this.title = title;
        this.caption = caption;
        this.captionParseMode = captionParseMode;
    }
}
