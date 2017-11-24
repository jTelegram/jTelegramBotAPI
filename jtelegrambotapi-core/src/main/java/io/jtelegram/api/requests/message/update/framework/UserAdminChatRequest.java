package io.jtelegram.api.requests.message.update.framework;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class UserAdminChatRequest extends UpdatableChatRequest {
    private final Integer userId;

    public UserAdminChatRequest(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId) {
        super(endPoint, errorHandler, callback, chatId);
        this.userId = userId;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && userId != null;
    }
}
