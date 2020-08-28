package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
public abstract class SendableMessageRequest<T> extends SendableChatRequest<T> {
    @Setter
    private Integer replyToMessageId;
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
