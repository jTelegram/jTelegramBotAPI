package io.jtelegram.api.message.sendable;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;

import java.util.function.Consumer;

public abstract class SendableMessageRequest<T> extends QueryTelegramRequest<T> {
    private final ChatID chatId;
    private final Integer replyToMessageId;
    private final Boolean disableNotification;
    private final ReplyMarkup replyMarkup;

    protected SendableMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatID chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler);
        this.chatId = chatId;
        this.replyToMessageId = replyToMessageId;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }

    public abstract SendableMessageType getType();

    @Override
    protected boolean isValid() {
        if (chatId == null) return false;
        return true;
    }
}
