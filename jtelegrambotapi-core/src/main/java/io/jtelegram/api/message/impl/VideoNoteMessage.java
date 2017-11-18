package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.VideoNote;
import lombok.Getter;

@Getter
public class VideoNoteMessage extends CaptionableMessage<VideoNote> {
    private VideoNote videoNote;

    @Override
    public VideoNote getContent() {
        return videoNote;
    }
}
