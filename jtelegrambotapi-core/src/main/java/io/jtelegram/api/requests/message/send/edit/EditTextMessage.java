package io.jtelegram.api.requests.message.send.edit;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.requests.message.send.framework.EditMessageRequest;
import io.jtelegram.api.requests.message.framework.ParseMode;
import io.jtelegram.api.requests.message.send.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
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
}
