package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class UserAdminChatRequest extends UpdatableChatRequest {
    private final Long userId;

    public UserAdminChatRequest(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Long userId) {
        super(endPoint, errorHandler, callback, chatId);
        this.userId = userId;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && userId != null;
    }
}
