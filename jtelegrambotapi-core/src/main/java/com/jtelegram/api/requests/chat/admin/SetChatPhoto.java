package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class SetChatPhoto extends InputFileMessageUpdate {
    private ChatId chatId;
    private InputFile photo;

    @Builder
    public SetChatPhoto(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, InputFile photo) {
        super("setChatPhoto", errorHandler, callback);
        this.chatId = chatId;
        this.photo = photo;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(photo);
    }

    @Override
    protected boolean isValid() {
        return chatId != null && photo != null;
    }
}
