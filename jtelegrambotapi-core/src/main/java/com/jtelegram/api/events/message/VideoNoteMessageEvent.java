package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VideoNoteMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class VideoNoteMessageEvent extends MessageEvent<VideoNoteMessage> {
    public VideoNoteMessageEvent(TelegramBot bot, Update.MessageUpdate update, VideoNoteMessage message) {
        super(bot, update, message);
    }
}
