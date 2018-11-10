package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.GameMessage;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.EditMessageRequest;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.util.TextBuilder;
import java.util.Objects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class EditTextMessage extends EditMessageRequest<TextMessage> {
    private String text;
    private ParseMode parseMode;
    private Boolean disableWebPagePreview;

    @Builder
    public EditTextMessage(Consumer<TextMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup, String text, ParseMode parseMode, boolean disableWebPagePreview) {
        super("editMessageText", TextMessage.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && text != null;
    }

    public static class EditTextMessageBuilder {
        public EditTextMessageBuilder text(TextBuilder builder) {
            this.parseMode = ParseMode.HTML;
            this.text = builder.toHtml();
            return this;
        }

        public EditTextMessageBuilder text(String text) {
            this.text = text;
            return this;
        }
    }

    /**
     * Creates a request builder to edit the text of the specified message.
     *
     * @param message the message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditTextMessageBuilder forMessage(TextMessage message) {
        Objects.requireNonNull(message, "text message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    /**
     * Creates a request builder to edit the text of the specified message.
     *
     * @param message the message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditTextMessageBuilder forMessage(GameMessage message) {
        Objects.requireNonNull(message, "game message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    /**
     * Creates a request builder to edit the text of the specified message.
     *
     * @param inlineMessageId the ID of the inline message that will be edited when the request is executed
     *
     * @return the request builder
     */
    public static EditTextMessageBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
