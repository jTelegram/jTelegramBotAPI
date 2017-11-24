package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import lombok.Builder;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
public class DeleteChatPhoto extends UpdatableChatRequest {
    @Builder
    public DeleteChatPhoto(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("deleteChatPhoto", errorHandler, callback, chatId);
    }
}
