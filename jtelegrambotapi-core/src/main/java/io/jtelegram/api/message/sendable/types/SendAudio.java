package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.AudioMessage;
import io.jtelegram.api.message.sendable.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import lombok.Builder;

import java.util.function.Consumer;

public class SendAudio extends SendableMessageRequest<AudioMessage> {
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
    public SendableMessageType getType() {
        return SendableMessageType.AUDIO;
    }

    public static class SendAudioBuilder {
        public SendAudio.SendAudioBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public SendAudio.SendAudioBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
