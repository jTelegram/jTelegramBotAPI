package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.AnimationMessage;
import com.jtelegram.api.update.Update;

public class AnimationMessageEvent extends MessageEvent<AnimationMessage> {
    public AnimationMessageEvent(TelegramBot bot, Update.MessageUpdate update, AnimationMessage message) {
        super(bot, update, message);
    }
}
