package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.VideoNote;
import com.jtelegram.api.requests.message.edit.EditMessageCaption;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class VideoNoteMessage extends CaptionableMessage<VideoNote> {
    private VideoNote videoNote;

    @Override
    public VideoNote getContent() {
        return videoNote;
    }

    public EditMessageCaption.EditMessageCaptionBuilder toEditCaptionRequest() {
        return EditMessageCaption.forMessage(this);
    }
}
