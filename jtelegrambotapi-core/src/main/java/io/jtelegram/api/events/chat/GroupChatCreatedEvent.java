package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.impl.service.GroupChatCreatedMessage;

public class GroupChatCreatedEvent extends ServiceMessageEvent<GroupChatCreatedMessage> {
    private Chat newChat;

    public GroupChatCreatedEvent(TelegramBot bot, GroupChatCreatedMessage originMessage) {
        super(bot, originMessage);
        this.newChat = originMessage.getChat();
    }
}
