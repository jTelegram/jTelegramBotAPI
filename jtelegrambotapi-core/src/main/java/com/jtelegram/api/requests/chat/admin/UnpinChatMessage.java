package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
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
