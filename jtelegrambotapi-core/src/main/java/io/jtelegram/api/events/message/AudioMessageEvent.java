package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.AudioMessage;

public class AudioMessageEvent extends MessageEvent<AudioMessage> {
    public AudioMessageEvent(TelegramBot bot, AudioMessage message) {
        super(bot, message);
    }
}
