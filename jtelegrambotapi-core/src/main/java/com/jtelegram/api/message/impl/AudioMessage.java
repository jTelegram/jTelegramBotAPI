package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.media.Audio;
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
