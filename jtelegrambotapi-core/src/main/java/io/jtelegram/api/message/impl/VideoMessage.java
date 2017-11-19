package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.Video;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VideoMessage extends CaptionableMessage<Video> {
    private Video video;

    @Override
    public Video getContent() {
        return video;
    }
}
