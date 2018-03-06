package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendDocument extends InputFileMessageRequest<DocumentMessage> {
    private final InputFile document;
    private final String caption;
    private final ParseMode parseMode;

    @Builder
    protected SendDocument(Consumer<DocumentMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile document, String caption, ParseMode parseMode) {
        super("sendDocument", DocumentMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.document = document;
        this.caption = caption;
        this.parseMode = parseMode;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && document != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(document);
    }

}
