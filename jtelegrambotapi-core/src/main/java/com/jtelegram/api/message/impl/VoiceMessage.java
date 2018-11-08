package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.Voice;
import com.jtelegram.api.requests.message.edit.EditMessageCaption;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class VoiceMessage extends CaptionableMessage<Voice> {
    private Voice voice;

    @Override
    public Voice getContent() {
        return voice;
    }

    public EditMessageCaption.EditMessageCaptionBuilder toEditCaptionRequest() {
        return EditMessageCaption.forMessage(this);
    }
}
