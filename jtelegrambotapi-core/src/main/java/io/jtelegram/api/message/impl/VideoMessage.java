package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.Video;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VideoMessage extends CaptionableMessage<Video> {
    private Video video;
    /**
     * This field does not exist in the Telegram Docs, however is
     * non-zero whenever a photo is a fragment of an album. Albums
     * are collection of images and videos that have a max size of
     * 10. The Telegram Bot has no evident way of checking when it
     * has received the whole album, so do with this as you will
     */
    private long mediaGroupId;

    @Override
    public Video getContent() {
        return video;
    }
}
