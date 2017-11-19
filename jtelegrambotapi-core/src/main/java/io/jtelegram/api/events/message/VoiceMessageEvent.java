package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.VoiceMessage;

public class VoiceMessageEvent extends MessageEvent<VoiceMessage> {
    public VoiceMessageEvent(TelegramBot bot, VoiceMessage message) {
        super(bot, message);
    }
}
