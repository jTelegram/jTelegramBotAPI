package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class UnpinChatMessage extends UpdatableChatRequest {
    @Builder
    public UnpinChatMessage(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("unpinChatMessage", errorHandler, callback, chatId);
    }
}
