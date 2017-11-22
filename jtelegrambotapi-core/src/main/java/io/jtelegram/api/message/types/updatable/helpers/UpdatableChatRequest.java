package io.jtelegram.api.message.types.updatable.helpers;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
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
