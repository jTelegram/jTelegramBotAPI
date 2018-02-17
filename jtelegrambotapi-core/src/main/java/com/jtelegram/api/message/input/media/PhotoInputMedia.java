package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;

public class PhotoInputMedia extends InputMedia {
    @Builder
    protected PhotoInputMedia(InputFile media, String caption, ParseMode parseMode) {
        super(InputMediaType.PHOTO, media, caption, parseMode);
    }
}
