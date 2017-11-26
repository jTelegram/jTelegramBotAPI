package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.VideoNote;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VideoNoteMessage extends CaptionableMessage<VideoNote> {
    private VideoNote videoNote;

    @Override
    public VideoNote getContent() {
        return videoNote;
    }
}
