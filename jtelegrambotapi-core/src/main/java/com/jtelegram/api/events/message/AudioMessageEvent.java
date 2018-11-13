package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class AudioMessageEvent extends MessageEvent<AudioMessage> {
    public AudioMessageEvent(TelegramBot bot, Update.MessageUpdate update, AudioMessage message) {
        super(bot, update, message);
    }
}
