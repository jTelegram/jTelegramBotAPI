package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.impl.GameMessage;
import com.jtelegram.api.requests.message.framework.req.EditMessageRequest;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import java.util.Objects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class EditMessageCaption extends EditMessageRequest<Message> {
    private String caption;

    @Builder
    public EditMessageCaption(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup, String caption) {
        super("editMessageCaption", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
        this.caption = caption;
    }

    /**
     * Creates a request builder to edit the caption of the specified message.
     *
     * @param message the message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditMessageCaptionBuilder forMessage(CaptionableMessage<?> message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    /**
     * Creates a request builder to edit the caption of the specified message.
     *
     * @param inlineMessageId the ID of the inline message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditMessageCaptionBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
