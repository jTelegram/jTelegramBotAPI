package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.impl.service.LeftChatMemberMessage;
import io.jtelegram.api.user.User;
import lombok.Getter;

@Getter
public class ChatMemberLeftEvent extends ServiceMessageEvent<LeftChatMemberMessage> {
    private User leftMember;

    public ChatMemberLeftEvent(TelegramBot bot, LeftChatMemberMessage originMessage) {
        super(bot, originMessage);
        this.leftMember = originMessage.getLeftChatMember();
    }
}
