package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UpdatableChatRequest;
import io.jtelegram.api.message.types.updatable.helpers.UserAdminChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class UnbanChatMember extends UserAdminChatRequest {
    @Builder
    public UnbanChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId) {
        super("unbanChatMember", errorHandler, callback, chatId, userId);
    }
}
