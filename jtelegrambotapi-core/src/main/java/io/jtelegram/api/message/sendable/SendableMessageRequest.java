package io.jtelegram.api.message.sendable;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.message.MessageType;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public abstract class SendableMessageRequest<T> extends SendableChatRequest<T> {
    private final Integer replyToMessageId;
    private final Boolean disableNotification;
    private final ReplyMarkup replyMarkup;

    protected SendableMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId);
        this.replyToMessageId = replyToMessageId;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }


    @Override
    protected boolean isValid() {
        return super.isValid();
    }
}
