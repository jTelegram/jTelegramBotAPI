package com.jtelegram.api.requests.chat;

import com.jtelegram.api.chat.ChatMember;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetChatAdministrators extends QueryTelegramRequest<ChatMember[]> {
    private ChatId chatId;

    @Builder
    public GetChatAdministrators(Consumer<ChatMember[]> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("getChatAdministrators", ChatMember[].class, callback, errorHandler);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
