package io.jtelegram.api.requests.message.send;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.VideoMessage;
import io.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import io.jtelegram.api.chat.id.ChatId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendVideo extends InputFileMessageRequest<VideoMessage> {
    private final InputFile video;
    private final Integer duration;
    private final Integer width;
    private final Integer height;
    private final String caption;

    @Builder
    protected SendVideo(Consumer<VideoMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile video, Integer duration, Integer width, Integer height, String caption) {
        super("sendVideo", VideoMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.video = video;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.caption = caption;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && video != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(video);
    }

}
