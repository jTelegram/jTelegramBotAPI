package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.user.User;
import com.jtelegram.api.message.impl.service.LeftChatMemberMessage;
import lombok.Getter;

@Getter
public class ChatMemberLeftEvent extends ServiceMessageEvent<LeftChatMemberMessage> {
    private User leftMember;

    public ChatMemberLeftEvent(TelegramBot bot, LeftChatMemberMessage originMessage) {
        super(bot, originMessage);
        this.leftMember = originMessage.getLeftChatMember();
    }
}
