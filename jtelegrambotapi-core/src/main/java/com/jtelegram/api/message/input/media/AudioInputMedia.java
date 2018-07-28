package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class AudioInputMedia extends ThumbableInputMedia {
    private long duration;
    private String performer;
    private String title;

    @Builder
    public AudioInputMedia(InputFile media, String caption, ParseMode parseMode,
                           LocalInputFile thumbnail, long duration, String performer,
                           String title) {
        super(InputMediaType.AUDIO, media, caption, parseMode, thumbnail);
        this.duration = duration;
        this.performer = performer;
        this.title = title;
    }
}
