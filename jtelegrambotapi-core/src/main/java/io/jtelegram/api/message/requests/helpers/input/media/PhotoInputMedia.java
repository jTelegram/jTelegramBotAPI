package io.jtelegram.api.message.requests.helpers.input.media;

import io.jtelegram.api.message.requests.helpers.input.file.InputFile;
import lombok.Builder;

public class PhotoInputMedia extends InputMedia {
    @Builder
    public PhotoInputMedia(InputFile media, String caption) {
        super(InputMediaType.PHOTO, media, caption);
    }
}
