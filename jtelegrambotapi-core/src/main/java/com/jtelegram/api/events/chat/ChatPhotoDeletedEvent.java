package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.DeleteChatPhotoMessage;

public class ChatPhotoDeletedEvent extends ServiceMessageEvent<DeleteChatPhotoMessage> {
    public ChatPhotoDeletedEvent(TelegramBot bot, DeleteChatPhotoMessage originMessage) {
        super(bot, originMessage);
    }
}
