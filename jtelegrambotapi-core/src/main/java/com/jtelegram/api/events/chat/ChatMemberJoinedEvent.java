package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.NewChatMembersMessage;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.user.User;
import lombok.Getter;

import java.util.List;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChatMemberJoinedEvent extends ServiceMessageEvent<NewChatMembersMessage> {
    private List<User> newMembers;

    public ChatMemberJoinedEvent(TelegramBot bot, Update.MessageUpdate update, NewChatMembersMessage originMessage) {
        super(bot, update, originMessage);
        this.newMembers = originMessage.getNewChatMembers();
    }
}
