package io.jtelegram.api.requests.message.send.message;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.VoiceMessage;
import io.jtelegram.api.requests.message.framework.InputFileMessageRequest;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.requests.message.send.framework.ReplyMarkup;
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
    private final Integer duration;

    @Builder
    protected SendVoice(Consumer<VoiceMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile voice, String caption, Integer duration) {
        super("sendVoice", VoiceMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.voice = voice;
        this.caption = caption;
        this.duration = duration;
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
