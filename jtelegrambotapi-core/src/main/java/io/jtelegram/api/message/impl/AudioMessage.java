package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.Audio;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AudioMessage extends CaptionableMessage<Audio> {
    private Audio audio;

    @Override
    public Audio getContent() {
        return audio;
    }
}
