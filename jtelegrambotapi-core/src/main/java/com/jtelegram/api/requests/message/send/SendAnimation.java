package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.AnimationMessage;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendAnimation extends InputFileMessageRequest<AnimationMessage> {
    private final InputFile animation;
    // the thumbnail must be sent via attach protocol
    // thumbnail width/height must not exceed 90px
    // must be less than 200 kB
    private final LocalInputFile thumbnail;
    private final Long duration;
    private final Integer width;
    private final Integer height;
    private final String caption;
    private final ParseMode parseMode;

    @Builder
    protected SendAnimation(Consumer<AnimationMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId,
                         Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile animation,
                         LocalInputFile thumbnail, Long duration, Integer width, Integer height, String caption, ParseMode parseMode) {
        super("sendAnimation", AnimationMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.animation = animation;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.caption = caption;
        this.parseMode = parseMode;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && animation != null;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Arrays.asList(animation, thumbnail);
    }
}
