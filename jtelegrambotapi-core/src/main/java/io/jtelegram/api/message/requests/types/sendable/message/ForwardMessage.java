package io.jtelegram.api.message.requests.types.sendable.message;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.requests.types.sendable.helpers.ReplyMarkup;
import io.jtelegram.api.message.requests.types.sendable.helpers.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class ForwardMessage extends SendableMessageRequest<Message> {
    private final ChatId fromChatId;
    private final Integer messageID;

    @Builder
    protected ForwardMessage(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ChatId fromChatId, Integer messageID, ReplyMarkup replyMarkup) {
        super("forwardMessage", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.fromChatId = fromChatId;
        this.messageID = messageID;
    }


    @Override
    protected boolean isValid() {
        return super.isValid() && fromChatId != null && messageID != null;
    }

    public static class ForwardMessageBuilder {
        public ForwardMessage.ForwardMessageBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public ForwardMessage.ForwardMessageBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}
