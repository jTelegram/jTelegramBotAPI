package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.req.UserAdminChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class UnbanChatMember extends UserAdminChatRequest {
    @Builder
    public UnbanChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Long userId) {
        super("unbanChatMember", errorHandler, callback, chatId, userId);
    }
}
