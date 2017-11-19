package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.VideoNoteMessage;

public class VideoNoteMessageEvent extends MessageEvent<VideoNoteMessage> {
    public VideoNoteMessageEvent(TelegramBot bot, VideoNoteMessage message) {
        super(bot, message);
    }
}
