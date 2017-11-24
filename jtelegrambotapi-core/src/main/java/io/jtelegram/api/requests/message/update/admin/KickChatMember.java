package io.jtelegram.api.requests.message.update.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.update.framework.UserAdminChatRequest;
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
