package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.DeleteChatPhotoMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class ChatPhotoDeletedEvent extends ServiceMessageEvent<DeleteChatPhotoMessage> {
    public ChatPhotoDeletedEvent(TelegramBot bot, Update.MessageUpdate update, DeleteChatPhotoMessage originMessage) {
        super(bot, update, originMessage);
    }
}
