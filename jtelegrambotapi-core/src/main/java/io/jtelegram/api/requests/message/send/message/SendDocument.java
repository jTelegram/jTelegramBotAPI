package io.jtelegram.api.requests.message.send.message;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.DocumentMessage;
import io.jtelegram.api.requests.message.framework.InputFileMessageRequest;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.requests.message.send.framework.ReplyMarkup;
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

    @Builder
    protected SendDocument(Consumer<DocumentMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile document, String caption) {
        super("sendDocument", DocumentMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.document = document;
        this.caption = caption;
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
