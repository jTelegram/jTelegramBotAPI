package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.requests.message.framework.req.InputFileMessageUpdate;
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
