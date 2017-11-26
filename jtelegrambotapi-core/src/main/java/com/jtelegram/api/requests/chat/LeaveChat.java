package com.jtelegram.api.requests.chat;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import com.jtelegram.api.ex.TelegramException;
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
