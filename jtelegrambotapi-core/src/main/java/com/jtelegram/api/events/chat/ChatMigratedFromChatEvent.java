package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.MigrateFromChatIdMessage;
import com.jtelegram.api.message.impl.MigrateToChatIdMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChatMigratedFromChatEvent extends ServiceMessageEvent<MigrateFromChatIdMessage> {
    private Chat newChat;

    public ChatMigratedFromChatEvent(TelegramBot bot, MigrateFromChatIdMessage originMessage) {
        super(bot, originMessage);
        this.newChat = originMessage.getChat();
    }
}
