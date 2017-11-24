package io.jtelegram.api.requests.chat;

import io.jtelegram.api.chat.ChatMember;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetChatAdministrators extends QueryTelegramRequest<ChatMember[]> {
    private ChatId chatId;

    public GetChatAdministrators(Consumer<ChatMember[]> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("getChatAdministrators", ChatMember[].class, callback, errorHandler);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
