package io.jtelegram.api.message.requests.types.sendable.message;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.requests.types.sendable.helpers.SendableInlineRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class StopMessageLiveLocation extends SendableInlineRequest<Message> {
    @Builder
    protected StopMessageLiveLocation(String endPoint, Class<Message> callbackType, Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer messageId, String inlineMessageId) {
        super(endPoint, callbackType, callback, errorHandler, chatId, messageId, inlineMessageId);
    }

    @Override
    protected boolean isValid() {
        return super.isValid();
    }
}
