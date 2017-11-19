package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.VideoMessage;

public class VideoMessageEvent extends MessageEvent<VideoMessage> {
    public VideoMessageEvent(TelegramBot bot, VideoMessage message) {
        super(bot, message);
    }
}
