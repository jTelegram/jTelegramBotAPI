package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VideoNoteMessage;

public class VideoNoteMessageEvent extends MessageEvent<VideoNoteMessage> {
    public VideoNoteMessageEvent(TelegramBot bot, VideoNoteMessage message) {
        super(bot, message);
    }
}
