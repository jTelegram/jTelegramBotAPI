package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.AudioMessage;

public class AudioMessageEvent extends MessageEvent<AudioMessage> {
    public AudioMessageEvent(TelegramBot bot, AudioMessage message) {
        super(bot, message);
    }
}
