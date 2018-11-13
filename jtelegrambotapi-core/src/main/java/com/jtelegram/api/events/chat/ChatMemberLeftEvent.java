package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.user.User;
import com.jtelegram.api.message.impl.service.LeftChatMemberMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChatMemberLeftEvent extends ServiceMessageEvent<LeftChatMemberMessage> {
    private User leftMember;

    public ChatMemberLeftEvent(TelegramBot bot, Update.MessageUpdate update, LeftChatMemberMessage originMessage) {
        super(bot, update, originMessage);
        this.leftMember = originMessage.getLeftChatMember();
    }
}
