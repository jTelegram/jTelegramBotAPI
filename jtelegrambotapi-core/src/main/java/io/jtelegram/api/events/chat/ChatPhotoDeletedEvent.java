package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.impl.service.DeleteChatPhotoMessage;

public class ChatPhotoDeletedEvent extends ServiceMessageEvent<DeleteChatPhotoMessage> {
    public ChatPhotoDeletedEvent(TelegramBot bot, DeleteChatPhotoMessage originMessage) {
        super(bot, originMessage);
    }
}
