package com.jtelegram.api.message.input.media;

import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoInputMedia extends InputMedia {
    private boolean supportsStreaming;
    private int width;
    private int height;
    private int duration;

    @Builder
    public VideoInputMedia(InputFile media, boolean supportsStreaming, String caption, ParseMode parseMode, int width, int height, int duration) {
        super(InputMediaType.VIDEO, media, caption, parseMode);
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.supportsStreaming = supportsStreaming;
    }
}
