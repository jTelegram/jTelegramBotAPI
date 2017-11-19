package io.jtelegram.api.message.sendable;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;

import java.util.function.Consumer;

public abstract class SendableMessageRequest<T> extends QueryTelegramRequest<T> {
    private final ChatID chatID;
    private final Integer replyToMessageID;
    private final Boolean disableNotification;
    private final ReplyMarkup replyMarkup;

    protected SendableMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatID chatID, Integer replyToMessageID, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler);
        this.chatID = chatID;
        this.replyToMessageID = replyToMessageID;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }

    public abstract SendableMessageType getType();

    @Override
    protected boolean isValid() {
        if (chatID == null) return false;
        return true;
    }
}
