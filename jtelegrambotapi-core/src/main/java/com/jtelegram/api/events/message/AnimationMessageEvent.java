package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.AnimationMessage;

public class AnimationMessageEvent extends MessageEvent<AnimationMessage> {
    public AnimationMessageEvent(TelegramBot bot, AnimationMessage message) {
        super(bot, message);
    }
}
