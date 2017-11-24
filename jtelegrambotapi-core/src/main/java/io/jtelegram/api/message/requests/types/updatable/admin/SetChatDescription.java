package io.jtelegram.api.message.requests.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.updatable.helpers.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class SetChatDescription extends UpdatableChatRequest {
    private final String description;

    @Builder
    public SetChatDescription( Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, String description) {
        super("setChatDescription", errorHandler, callback, chatId);
        this.description = description;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && description != null && description.length() < 256;
    }
}
