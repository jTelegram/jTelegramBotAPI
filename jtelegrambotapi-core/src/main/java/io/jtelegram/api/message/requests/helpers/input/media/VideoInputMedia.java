package io.jtelegram.api.message.requests.helpers.input.media;

import io.jtelegram.api.message.requests.helpers.input.file.InputFile;

public class VideoInputMedia extends InputMedia {
    private int width;
    private int height;
    private int duration;

    public VideoInputMedia(InputFile media, String caption, int width, int height, int duration) {
        super(InputMediaType.VIDEO, media, caption);
        this.width = width;
        this.height = height;
        this.duration = duration;
    }
}
