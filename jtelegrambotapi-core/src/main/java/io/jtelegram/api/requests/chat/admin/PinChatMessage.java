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
public class PinChatMessage extends UpdatableChatRequest {
    private final Integer messageId;
    private final Boolean disableNotification;

    @Builder
    public PinChatMessage(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer messageId, Boolean disableNotification) {
        super("pinChatMessage", errorHandler, callback, chatId);
        this.messageId = messageId;
        this.disableNotification = disableNotification;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && messageId != null;
    }
}
