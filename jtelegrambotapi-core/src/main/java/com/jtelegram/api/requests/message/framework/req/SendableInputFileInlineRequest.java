package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFileRequest;

import java.net.http.HttpRequest;
import java.util.function.Consumer;

public abstract class SendableInputFileInlineRequest<T> extends SendableInlineRequest<T> implements InputFileRequest {
    public SendableInputFileInlineRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler,
                                          ChatId chatId, Integer messageId, String inlineMessageId) {
        super(endPoint, callbackType, callback, errorHandler, chatId, messageId, inlineMessageId);
    }

    @Override
    public HttpRequest.Builder superBuild(TelegramBot bot) {
        return super.build(bot);
    }
}
