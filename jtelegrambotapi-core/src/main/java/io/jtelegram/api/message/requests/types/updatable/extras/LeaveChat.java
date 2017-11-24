package io.jtelegram.api.message.requests.types.updatable.extras;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.updatable.helpers.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class LeaveChat extends UpdatableChatRequest {
    @Builder
    public LeaveChat(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("endPoint", errorHandler, callback, chatId);
    }
}
