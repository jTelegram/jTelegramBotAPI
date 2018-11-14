package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.SupergroupChatCreatedMessage;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SupergroupChatCreatedEvent extends ServiceMessageEvent<SupergroupChatCreatedMessage> {
    private Chat newChat;

    public SupergroupChatCreatedEvent(TelegramBot bot, Update.MessageUpdate update, SupergroupChatCreatedMessage originMessage) {
        super(bot, update, originMessage);
        this.newChat = originMessage.getChat();
    }
}
