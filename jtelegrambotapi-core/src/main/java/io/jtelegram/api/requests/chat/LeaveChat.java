package io.jtelegram.api.requests.chat;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class LeaveChat extends UpdatableChatRequest {
    @Builder
    public LeaveChat(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("leaveChat", errorHandler, callback, chatId);
    }
}
