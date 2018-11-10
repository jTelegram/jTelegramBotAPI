package com.jtelegram.api.requests.message;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ForwardMessage extends SendableMessageRequest<Message> {
    private final ChatId fromChatId;
    private final Integer messageId;

    @Builder
    protected ForwardMessage(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ChatId fromChatId, Integer messageId, ReplyMarkup replyMarkup) {
        super("forwardMessage", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.fromChatId = fromChatId;
        this.messageId = messageId;
    }


    @Override
    protected boolean isValid() {
        return super.isValid() && fromChatId != null && messageId != null;
    }

    /**
     * Creates a request builder to forward the specified message.
     *
     * @param message the message that will be forwarded when the request is executed
     *
     * @return the request builder
     */
    public static ForwardMessageBuilder forMessage(Message<?> message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

}
