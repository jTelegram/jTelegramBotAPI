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
    private final String text;
    private final ParseMode parseMode;
    private final Boolean disableWebPagePreview;

    @Builder
    protected SendText(Consumer<TextMessage> callback, Consumer<TelegramException> errorHandler, ChatID chatId, Integer replyToMessageID, Boolean disableNotification, String text, ParseMode parseMode, Boolean disableWebPagePreview, ReplyMarkup replyMarkup) {
        super("sendMessage", TextMessage.class, callback, errorHandler, chatId, replyToMessageID, disableNotification, replyMarkup);
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }


    @Override
    public SendableMessageType getType() {
        return SendableMessageType.TEXT;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && text != null;
    }


    public static class SendTextBuilder {
        public SendTextBuilder chatID(Chat chat) {
            this.chatId = new LongChatID(chat.getId());
            return this;
        }

        public SendTextBuilder chatID(ChatID chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}