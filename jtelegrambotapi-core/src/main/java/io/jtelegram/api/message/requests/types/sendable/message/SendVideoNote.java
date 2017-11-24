package io.jtelegram.api.message.requests.types.sendable.message;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.media.VideoNote;
import io.jtelegram.api.message.requests.types.sendable.helpers.InputFileMessageRequest;
import io.jtelegram.api.message.requests.helpers.input.file.InputFile;
import io.jtelegram.api.message.requests.types.sendable.helpers.ReplyMarkup;
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
