package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.VideoMessage;
import com.jtelegram.api.TelegramBot;

public class VideoMessageEvent extends MessageEvent<VideoMessage> {
    public VideoMessageEvent(TelegramBot bot, VideoMessage message) {
        super(bot, message);
    }
}
