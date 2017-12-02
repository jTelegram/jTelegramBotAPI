package com.jtelegram.api.requests.message;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.req.SendableInlineRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class StopMessageLiveLocation extends SendableInlineRequest<Message> {
    @Builder
    protected StopMessageLiveLocation(Class<Message> callbackType, Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer messageId, String inlineMessageId) {
        super("stopMessageLiveLocation", callbackType, callback, errorHandler, chatId, messageId, inlineMessageId);
    }

    @Override
    protected boolean isValid() {
        return super.isValid();
    }
}
