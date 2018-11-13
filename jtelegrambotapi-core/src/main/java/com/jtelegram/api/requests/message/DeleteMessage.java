package com.jtelegram.api.requests.message;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
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

    /**
     * Creates a request builder to delete the specified message.
     *
     * @param message the message that will be deleted when the request is executed
     *
     * @return the request builder
     */
    public static DeleteMessageBuilder forMessage(Message<?> message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }
}
