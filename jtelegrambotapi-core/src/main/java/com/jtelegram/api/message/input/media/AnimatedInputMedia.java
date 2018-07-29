package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public abstract class AnimatedInputMedia extends ThumbableInputMedia {
    private int width;
    private int height;
    private long duration;

    protected AnimatedInputMedia(InputMediaType type, InputFile media, String caption, ParseMode parseMode, LocalInputFile thumbnail,
                              int width, int height, long duration) {
        super(type, media, caption, parseMode, thumbnail);
        this.width = width;
        this.height = height;
        this.duration = duration;
    }
}
