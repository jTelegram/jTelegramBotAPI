package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.UserAdminChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class UnbanChatMember extends UserAdminChatRequest {
    @Builder
    public UnbanChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId) {
        super("unbanChatMember", errorHandler, callback, chatId, userId);
    }
}
