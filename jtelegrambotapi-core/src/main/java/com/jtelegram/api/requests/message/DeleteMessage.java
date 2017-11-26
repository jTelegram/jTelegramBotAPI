package com.jtelegram.api.requests.message;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class DeleteMessage extends UpdatableChatRequest {
    private Integer messageId;

    @Builder
    public DeleteMessage(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, int messageId) {
        super("deleteMessage", errorHandler, callback, chatId);
        this.messageId = messageId;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && messageId != null;
    }
}
