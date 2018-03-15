package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.AudioMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class AudioMessageEvent extends MessageEvent<AudioMessage> {
    public AudioMessageEvent(TelegramBot bot, AudioMessage message) {
        super(bot, message);
    }
}
