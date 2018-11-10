package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.EditMessageRequest;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class EditMessageReplyMarkup extends EditMessageRequest<Message> {
    @Builder
    public EditMessageReplyMarkup(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup) {
        super("editMessageReplyMarkup", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
    }

    /**
     * Creates a request builder to edit the reply markup of the specified message.
     *
     * @param message the message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditMessageReplyMarkupBuilder forMessage(Message<?> message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    /**
     * Creates a request builder to edit the reply markup of the specified message.
     *
     * @param inlineMessageId the ID of the inline message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditMessageReplyMarkupBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
