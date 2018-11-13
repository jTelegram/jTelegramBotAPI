package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.VideoMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class VideoMessageEvent extends MessageEvent<VideoMessage> {
    public VideoMessageEvent(TelegramBot bot, Update.MessageUpdate update, VideoMessage message) {
        super(bot, update, message);
    }
}
