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
