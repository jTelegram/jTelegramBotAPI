package com.jtelegram.api.events.chat;

import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.NewChatTitleMessage;
import com.jtelegram.api.TelegramBot;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NewChatTitleEvent extends ServiceMessageEvent<NewChatTitleMessage> {
    private String newTitle;

    public NewChatTitleEvent(TelegramBot bot, NewChatTitleMessage originMessage) {
        super(bot, originMessage);
        this.newTitle = originMessage.getNewChatTitle();
    }
}
