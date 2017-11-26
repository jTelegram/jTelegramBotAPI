package com.jtelegram.api.requests.chat;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetChat extends QueryTelegramRequest<Chat> {
    private ChatId chatId;

    @Builder
    protected GetChat(Consumer<Chat> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("getChat", Chat.class, callback, errorHandler);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
