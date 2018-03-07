package com.jtelegram.api.requests.chat;

import com.jtelegram.api.chat.ChatAction;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendChatAction extends UpdateTelegramRequest {
    private ChatId chatId;
    private ChatAction action;

    @Builder
    public SendChatAction(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, ChatAction action) {
        super("sendChatAction", errorHandler, callback);
        this.chatId = chatId;
        this.action = action;
    }

    @Override
    protected boolean isValid() {
        return chatId != null && action != null;
    }
}
