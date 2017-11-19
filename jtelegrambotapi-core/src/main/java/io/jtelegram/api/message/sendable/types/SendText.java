package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.message.sendable.ParseMode;
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
    private final boolean disableWebPagePreview;


    @Builder
    protected SendText(String endPoint, Class<TextMessage> callbackType, Consumer<TextMessage> callback, Consumer<TelegramException> errorHandler, ChatID chatID, int replyToMessageID, boolean disableNotification, String message, ParseMode parseMode, boolean disableWebPagePreview) {
        super(endPoint, callbackType, callback, errorHandler, chatID, replyToMessageID, disableNotification);
        this.message = message;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }
    // TODO replyMarkup


    @Override
    public SendableMessageType getType() {
        return SendableMessageType.TEXT;
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