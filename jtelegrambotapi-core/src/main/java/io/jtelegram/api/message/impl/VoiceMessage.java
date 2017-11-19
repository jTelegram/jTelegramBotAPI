package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.Voice;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VoiceMessage extends CaptionableMessage<Voice> {
    private Voice voice;

    @Override
    public Voice getContent() {
        return voice;
    }
}
