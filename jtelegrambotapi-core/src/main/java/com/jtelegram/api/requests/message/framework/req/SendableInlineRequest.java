package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendableInlineRequest<T> extends QueryTelegramRequest<T> {

    private final ChatId chatId;
    private final Integer messageId;
    private final String inlineMessageId;

    protected SendableInlineRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer messageId, String inlineMessageId) {
        super(endPoint, callbackType, callback, errorHandler);
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
    }

    @Override
    protected boolean isValid() {
        if (inlineMessageId == null) {
            return chatId != null && messageId != null;
        }

        if (chatId == null || messageId == null) {
            return true;
        }

        return false;
    }
}
