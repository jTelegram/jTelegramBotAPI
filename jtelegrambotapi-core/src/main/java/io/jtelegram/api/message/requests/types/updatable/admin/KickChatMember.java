package io.jtelegram.api.message.requests.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.updatable.helpers.UserAdminChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class KickChatMember extends UserAdminChatRequest {
    private final Long untilDate;

    @Builder
    public KickChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId, Long untilDate) {
        super("kickChatMember", errorHandler, callback, chatId, userId);
        this.untilDate = untilDate;
    }
}
