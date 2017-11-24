package io.jtelegram.api.requests.chat;

import io.jtelegram.api.chat.ChatMember;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetChatMember extends QueryTelegramRequest<ChatMember> {
    private ChatId chatId;
    private Integer userId;

    @Builder
    protected GetChatMember(Consumer<ChatMember> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer userId) {
        super("getChatMember", ChatMember.class, callback, errorHandler);
        this.chatId = chatId;
        this.userId = userId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null && userId != null;
    }
}
