package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.PhotoMessage;
import io.jtelegram.api.message.sendable.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.message.sendable.chatid.LongChatID;
import lombok.Builder;

import java.util.function.Consumer;

public class SendPhoto extends SendableMessageRequest<PhotoMessage> {
    private final InputFile photo;
    private final String caption;

    @Builder
    protected SendPhoto(Consumer<PhotoMessage> callback, Consumer<TelegramException> errorHandler, ChatID chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile photo, String caption) {
        super("sendPhoto", PhotoMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.photo = photo;
        this.caption = caption;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && photo != null;
    }

    @Override
    public SendableMessageType getType() {
        return SendableMessageType.PHOTO;
    }

    public static class SendPhotoBuilder {
        public SendPhoto.SendPhotoBuilder chatID(Chat chat) {
            this.chatId = new LongChatID(chat.getId());
            return this;
        }

        public SendPhoto.SendPhotoBuilder chatID(ChatID chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
