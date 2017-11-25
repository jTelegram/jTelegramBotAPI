package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.impl.service.NewChatMembersMessage;
import io.jtelegram.api.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatMemberJoinedEvent extends ServiceMessageEvent<NewChatMembersMessage> {
    private List<User> newMembers;

    public ChatMemberJoinedEvent(TelegramBot bot, NewChatMembersMessage originMessage) {
        super(bot, originMessage);
        this.newMembers = originMessage.getNewChatMembers();
    }
}
