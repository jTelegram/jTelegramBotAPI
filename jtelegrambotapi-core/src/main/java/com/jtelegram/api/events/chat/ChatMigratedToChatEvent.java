package com.jtelegram.api.events.chat;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.MigrateToChatIdMessage;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChatMigratedToChatEvent extends ServiceMessageEvent<MigrateToChatIdMessage> {
    private Chat newChat;

    public ChatMigratedToChatEvent(TelegramBot bot, Update.MessageUpdate update, MigrateToChatIdMessage originMessage) {
        super(bot, update, originMessage);
        this.newChat = originMessage.getChat();
    }
}
