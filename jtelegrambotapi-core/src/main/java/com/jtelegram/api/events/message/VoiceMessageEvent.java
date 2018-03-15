package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VoiceMessage;
import lombok.ToString;

@ToString(callSuper = true)
public class VoiceMessageEvent extends MessageEvent<VoiceMessage> {
    public VoiceMessageEvent(TelegramBot bot, VoiceMessage message) {
        super(bot, message);
    }
}
