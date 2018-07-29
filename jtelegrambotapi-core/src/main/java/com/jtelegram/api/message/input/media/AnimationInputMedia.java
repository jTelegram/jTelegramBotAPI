package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.ToString;

@ToString(callSuper = true)
public class AnimationInputMedia extends AnimatedInputMedia {
    @Builder
    public AnimationInputMedia(InputFile media, String caption, ParseMode parseMode,
                               LocalInputFile thumbnail, int width, int height, long duration) {
        super(InputMediaType.ANIMATION, media, caption, parseMode, thumbnail, width, height, duration);
    }
}
