package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.message.sendable.ParseMode;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import io.jtelegram.api.message.sendable.SendableMessageType;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.message.sendable.chatid.LongChatID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class SendText extends SendableMessageRequest<TextMessage> {
    private final String message;
    private final ParseMode parseMode;
    private final Boolean disableWebPagePreview;
    private final ReplyMarkup replyMarkup;

    @Builder
    protected SendText(Consumer<TextMessage> callback, Consumer<TelegramException> errorHandler, ChatID chatID, Integer replyToMessageID, Boolean disableNotification, String message, ParseMode parseMode, Boolean disableWebPagePreview, ReplyMarkup replyMarkup) {
        super("sendMessage", TextMessage.class, callback, errorHandler, chatID, replyToMessageID, disableNotification, replyMarkup);
        this.message = message;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
        this.replyMarkup = replyMarkup;
    }


    @Override
    public SendableMessageType getType() {
        return SendableMessageType.TEXT;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && message != null;
    }


    public static class SendTextBuilder {
        public SendTextBuilder chatID(Chat chat) {
            this.chatID = new LongChatID(chat.getId());
            return this;
        }

        public SendTextBuilder chatID(ChatID chatID) {
            this.chatID = chatID;
            return this;
        }
    }
}