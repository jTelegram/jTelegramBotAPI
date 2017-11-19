package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.message.sendable.chatid.LongChatID;
import lombok.Builder;

import java.util.function.Consumer;

public class ForwardMessage extends SendableMessageRequest<Message> {
    private final ChatID fromChatID;
    private final Integer messageID;

    @Builder
    protected ForwardMessage(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatID chatId, Integer replyToMessageId, Boolean disableNotification, ChatID fromChatID, Integer messageID, ReplyMarkup replyMarkup) {
        super("forwardMessage", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.fromChatID = fromChatID;
        this.messageID = messageID;
    }

    @Override
    public SendableMessageType getType() {
        return SendableMessageType.FORWARD;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && fromChatID != null && messageID != null;
    }

    public static class ForwardMessageBuilder {
        public ForwardMessage.ForwardMessageBuilder chatID(Chat chat) {
            this.chatId = new LongChatID(chat.getId());
            return this;
        }

        public ForwardMessage.ForwardMessageBuilder chatID(ChatID chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
