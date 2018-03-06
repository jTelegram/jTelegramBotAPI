package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.VideoMessage;
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
public class SendVideo extends InputFileMessageRequest<VideoMessage> {
    private final InputFile video;
    private final Boolean supportsStreaming;
    private final Integer duration;
    private final Integer width;
    private final Integer height;
    private final String caption;
    private final ParseMode parseMode;

    @Builder
    protected SendVideo(Consumer<VideoMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile video, Boolean supportsStreaming, Integer duration, Integer width, Integer height, String caption, ParseMode parseMode) {
        super("sendVideo", VideoMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.video = video;
        this.supportsStreaming = supportsStreaming;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.caption = caption;
        this.parseMode = parseMode;
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
