package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.AudioMessage;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
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
public class SendAudio extends InputFileMessageRequest<AudioMessage> {
    private final InputFile audio;
    private final String caption;
    private final ParseMode parseMode;
    private final Integer duration;
    private final String title;

    @Builder
    protected SendAudio(Consumer<AudioMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageID, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile audio, String caption, Integer duration, String title, ParseMode parseMode) {
        super("sendAudio", AudioMessage.class, callback, errorHandler, chatId, replyToMessageID, disableNotification, replyMarkup);
        this.audio = audio;
        this.caption = caption;
        this.duration = duration;
        this.title = title;
        this.parseMode = parseMode;
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
