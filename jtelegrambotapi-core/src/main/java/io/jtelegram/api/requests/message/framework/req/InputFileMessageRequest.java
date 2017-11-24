package io.jtelegram.api.requests.message.framework.req;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.input.file.InputFileRequest;
import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import okhttp3.Request;

import java.util.function.Consumer;

public abstract class InputFileMessageRequest<T> extends SendableMessageRequest<T> implements InputFileRequest {
    protected InputFileMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
    }

    @Override
    public Request.Builder build(TelegramBot bot) {
        return getBuilder(bot);
    }

    @Override
    public Request.Builder superBuild(TelegramBot bot) {
        return super.build(bot);
    }
}
