package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.VoiceMessage;
import io.jtelegram.api.message.sendable.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.message.sendable.chatid.LongChatID;
import lombok.Builder;

import java.util.function.Consumer;

public class SendVoice extends SendableMessageRequest<VoiceMessage> {
    private final InputFile voice;
    private final String caption;
    private final Integer duration;

    @Builder
    protected SendVoice(Consumer<VoiceMessage> callback, Consumer<TelegramException> errorHandler, ChatID chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile voice, String caption, Integer duration) {
        super("sendVoice", VoiceMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.voice = voice;
        this.caption = caption;
        this.duration = duration;
    }

    @Override
    public SendableMessageType getType() {
        return null;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && voice != null;
    }

    public static class SendVoiceBuilder {
        public SendVoiceBuilder chatId(Chat chat) {
            this.chatId = new LongChatID(chat.getId());
            return this;
        }

        public SendVoice.SendVoiceBuilder chatId(ChatID chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
