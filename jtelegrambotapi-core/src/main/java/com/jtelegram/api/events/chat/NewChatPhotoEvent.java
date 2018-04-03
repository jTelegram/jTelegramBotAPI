package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.NewChatPhotoMessage;
import com.jtelegram.api.message.media.PhotoSize;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class NewChatPhotoEvent extends ServiceMessageEvent<NewChatPhotoMessage> {
    private List<PhotoSize> newChatPhoto;

    public NewChatPhotoEvent(TelegramBot bot, NewChatPhotoMessage originMessage) {
        super(bot, originMessage);
        this.newChatPhoto = originMessage.getNewChatPhoto();
    }
}
