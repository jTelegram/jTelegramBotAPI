package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class DeleteChatPhoto extends UpdatableChatRequest {
    @Builder
    public DeleteChatPhoto(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("deleteChatPhoto", errorHandler, callback, chatId);
    }
}
