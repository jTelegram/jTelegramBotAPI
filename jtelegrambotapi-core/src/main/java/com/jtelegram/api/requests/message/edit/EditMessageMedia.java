package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.media.InputMedia;
import com.jtelegram.api.requests.message.framework.req.SendableInputFileInlineRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class EditMessageMedia extends SendableInputFileInlineRequest<Message> {
    private InputMedia media;

    @Builder
    protected EditMessageMedia(Consumer<Message> callback, Consumer<TelegramException> errorHandler,
                               ChatId chatId, Integer messageId, String inlineMessageId, InputMedia media) {
        super("editMessageMedia", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId);
        this.media = media;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && media != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(media.getMedia());
    }
}
