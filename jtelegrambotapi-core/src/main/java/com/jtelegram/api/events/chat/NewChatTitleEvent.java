package com.jtelegram.api.events.chat;

import com.jtelegram.api.events.message.ServiceMessageEvent;
import com.jtelegram.api.message.impl.service.NewChatTitleMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class NewChatTitleEvent extends ServiceMessageEvent<NewChatTitleMessage> {
    private String newTitle;

    public NewChatTitleEvent(TelegramBot bot, Update.MessageUpdate update, NewChatTitleMessage originMessage) {
        super(bot, update, originMessage);
        this.newTitle = originMessage.getNewChatTitle();
    }
}
