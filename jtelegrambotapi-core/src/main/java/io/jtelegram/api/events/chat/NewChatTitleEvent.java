package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.impl.service.NewChatTitleMessage;
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
