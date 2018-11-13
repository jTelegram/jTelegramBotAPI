package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VoiceMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class VoiceMessageEvent extends MessageEvent<VoiceMessage> {
    public VoiceMessageEvent(TelegramBot bot, Update.MessageUpdate update, VoiceMessage message) {
        super(bot, update, message);
    }
}
