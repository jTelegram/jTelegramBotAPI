package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFileRequest;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;

import java.net.http.HttpRequest;
import java.util.function.Consumer;

public abstract class InputFileMessageRequest<T> extends SendableMessageRequest<T> implements InputFileRequest {
    protected InputFileMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
    }

    @Override
    public HttpRequest.Builder build(TelegramBot bot) {
        return getBuilder(bot);
    }

    @Override
    public HttpRequest.Builder superBuild(TelegramBot bot) {
        return super.build(bot);
    }
}
