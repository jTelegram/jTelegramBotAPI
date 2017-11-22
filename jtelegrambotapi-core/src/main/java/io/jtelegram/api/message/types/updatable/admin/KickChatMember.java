package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UpdatableChatRequest;

import java.util.function.Consumer;

public class KickChatMember extends UpdatableChatRequest {
    private final Integer userId;
    private final Long untilDate;

    public KickChatMember(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId, Long untilDate) {
        super(endPoint, errorHandler, callback, chatId);
        this.userId = userId;
        this.untilDate = untilDate;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && userId != null;
    }
}
