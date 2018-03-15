package com.jtelegram.api.events.chat;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.GroupChatCreatedMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GroupChatCreatedEvent extends ServiceMessageEvent<GroupChatCreatedMessage> {
    private Chat newChat;

    public GroupChatCreatedEvent(TelegramBot bot, GroupChatCreatedMessage originMessage) {
        super(bot, originMessage);
        this.newChat = originMessage.getChat();
    }
}
