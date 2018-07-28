package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.ToString;

@ToString(callSuper = true)
public class DocumentInputMedia extends ThumbableInputMedia {
    public DocumentInputMedia(InputFile media, String caption, ParseMode parseMode, LocalInputFile thumbnail) {
        super(InputMediaType.DOCUMENT, media, caption, parseMode, thumbnail);
    }
}
