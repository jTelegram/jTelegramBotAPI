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
