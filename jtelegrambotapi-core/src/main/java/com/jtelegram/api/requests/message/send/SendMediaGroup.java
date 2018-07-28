package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.media.InputMedia;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class SendMediaGroup extends InputFileMessageRequest<Message[]> {
    private final List<InputMedia> media;

    @Builder
    protected SendMediaGroup(Consumer<Message[]> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, List<InputMedia> media) {
        super("sendMediaGroup", Message[].class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.media = media;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && media != null && media.size() != 0;
    }

    @Override
    public List<InputFile> getInputFiles() {
        List<InputFile> allInputFiles = new ArrayList<>();
        media.stream().map(InputMedia::getAllMedia).forEach(allInputFiles::addAll);
        return allInputFiles;
    }

}
