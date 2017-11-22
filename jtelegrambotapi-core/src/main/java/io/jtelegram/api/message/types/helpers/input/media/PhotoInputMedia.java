package io.jtelegram.api.message.types.helpers.input.media;

import io.jtelegram.api.message.types.helpers.input.file.InputFile;
import lombok.Builder;

public class PhotoInputMedia extends InputMedia {
    @Builder
    public PhotoInputMedia(InputFile media, String caption) {
        super(InputMediaType.PHOTO, media, caption);
    }
}
