package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.VideoMessage;
import io.jtelegram.api.message.sendable.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;
@ToString
@Getter
public class SendVideo extends SendableMessageRequest<VideoMessage> {
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
    public SendableMessageType getType() {
        return SendableMessageType.VIDEO;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && video != null;
    }

    public static class SendVideoBuilder {
        public SendVideo.SendVideoBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public SendVideo.SendVideoBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
