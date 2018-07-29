package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.media.Animation;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class AnimationMessage extends Message<Animation> {
    private Animation animation;

    @Override
    public Animation getContent() {
        return animation;
    }
}
