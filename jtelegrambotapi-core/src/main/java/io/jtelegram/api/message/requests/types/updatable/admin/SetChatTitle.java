package io.jtelegram.api.message.requests.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.updatable.helpers.UpdatableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class SetChatTitle extends UpdatableChatRequest {
    private final String title;

    @Builder
    public SetChatTitle(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, String title) {
        super("setChatTitle", errorHandler, callback, chatId);
        this.title = title;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && title != null;
    }
}
