package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.PhotoMessage;
import io.jtelegram.api.message.sendable.InputFileMessageRequest;
import io.jtelegram.api.message.sendable.input.file.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class SendPhoto extends InputFileMessageRequest<PhotoMessage> {
    private final InputFile photo;
    private final String caption;

    @Builder
    protected SendPhoto(Consumer<PhotoMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile photo, String caption) {
        super("sendPhoto", PhotoMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.photo = photo;
        this.caption = caption;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && photo != null;
    }

    @Override
    public InputFile getInputFile() {
        return photo;
    }

    public static class SendPhotoBuilder {
        public SendPhoto.SendPhotoBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public SendPhoto.SendPhotoBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
