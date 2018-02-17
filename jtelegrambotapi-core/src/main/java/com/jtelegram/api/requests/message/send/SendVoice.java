package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.impl.VoiceMessage;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendVoice extends InputFileMessageRequest<VoiceMessage> {
    private final InputFile voice;
    private final String caption;
    private final ParseMode parseMode;
    private final Integer duration;

    @Builder
    protected SendVoice(Consumer<VoiceMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile voice, String caption, Integer duration, ParseMode parseMode) {
        super("sendVoice", VoiceMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.voice = voice;
        this.caption = caption;
        this.duration = duration;
        this.parseMode = parseMode;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && voice != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(voice);
    }

}
