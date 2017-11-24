package io.jtelegram.api.requests.message.update.extras;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.update.framework.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class LeaveChat extends UpdatableChatRequest {
    @Builder
    public LeaveChat(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("leaveChat", errorHandler, callback, chatId);
    }
}
