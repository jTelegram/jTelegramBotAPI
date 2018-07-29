package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class VideoInputMedia extends AnimatedInputMedia {
    private boolean supportsStreaming;

    @Builder
    public VideoInputMedia(InputFile media, String caption, ParseMode parseMode, LocalInputFile thumbnail,
                           int width, int height, long duration, boolean supportsStreaming) {
        super(InputMediaType.VIDEO, media, caption, parseMode, thumbnail, width, height, duration);
        this.supportsStreaming = supportsStreaming;
    }
}
