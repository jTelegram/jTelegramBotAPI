package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.media.VideoNote;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendVideoNote extends InputFileMessageRequest<VideoNote> {
    private final InputFile videoNote;
    private final Integer duration;
    private final Integer length;

    @Builder
    protected SendVideoNote(Consumer<VideoNote> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile videoNote, Integer duration, Integer length) {
        super("sendVideoNote", VideoNote.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.videoNote = videoNote;
        this.duration = duration;
        this.length = length;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && videoNote != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(videoNote);
    }

}
