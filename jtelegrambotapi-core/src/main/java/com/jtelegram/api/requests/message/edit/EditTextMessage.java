package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.EditMessageRequest;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
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
