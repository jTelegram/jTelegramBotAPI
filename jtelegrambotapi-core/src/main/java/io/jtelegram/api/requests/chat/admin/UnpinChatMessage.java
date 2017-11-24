package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class UnpinChatMessage extends UpdatableChatRequest {
    @Builder
    public UnpinChatMessage(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("unpinChatMessage", errorHandler, callback, chatId);
    }
}
