package io.jtelegram.api.message.requests.types.sendable.message;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.AudioMessage;
import io.jtelegram.api.message.requests.types.sendable.helpers.InputFileMessageRequest;
import io.jtelegram.api.message.requests.helpers.input.file.InputFile;
import io.jtelegram.api.message.requests.types.sendable.helpers.ReplyMarkup;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendAudio extends InputFileMessageRequest<AudioMessage> {
    private final InputFile audio;
    private final String caption;
    private final Integer duration;
    private final String title;

    @Builder
    protected SendAudio(Consumer<AudioMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageID, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile audio, String caption, Integer duration, String title) {
        super("sendAudio", AudioMessage.class, callback, errorHandler, chatId, replyToMessageID, disableNotification, replyMarkup);
        this.audio = audio;
        this.caption = caption;
        this.duration = duration;
        this.title = title;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && audio != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(audio);
    }

}
