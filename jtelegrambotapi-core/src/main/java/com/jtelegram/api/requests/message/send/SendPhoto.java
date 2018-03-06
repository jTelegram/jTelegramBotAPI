package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.PhotoMessage;
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
public class SendPhoto extends InputFileMessageRequest<PhotoMessage> {
    private final InputFile photo;
    private final String caption;
    private final ParseMode parseMode;

    @Builder
    protected SendPhoto(Consumer<PhotoMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile photo, String caption, ParseMode parseMode) {
        super("sendPhoto", PhotoMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.photo = photo;
        this.caption = caption;
        this.parseMode = parseMode;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && photo != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(photo);
    }

}
