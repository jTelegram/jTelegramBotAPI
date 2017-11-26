package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class UpdatableChatRequest extends UpdateTelegramRequest {
    private final ChatId chatId;

    public UpdatableChatRequest(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super(endPoint, errorHandler, callback);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
