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
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class EditMessageCaption extends EditMessageRequest<Message> {
    private String caption;

    @Builder
    public EditMessageCaption(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup, String caption) {
        super("editMessageCaption", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
        this.caption = caption;
    }

    public static EditMessageCaptionBuilder forMessage(CaptionableMessage<?> message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageCaptionBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
