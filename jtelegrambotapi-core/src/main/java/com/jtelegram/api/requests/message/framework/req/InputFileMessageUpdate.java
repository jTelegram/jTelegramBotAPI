package com.jtelegram.api.requests.message.framework.req;

import com.jtelegram.api.message.input.file.InputFileRequest;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;

import java.net.http.HttpRequest;
import java.util.function.Consumer;

public abstract class InputFileMessageUpdate extends UpdateTelegramRequest implements InputFileRequest {
    protected InputFileMessageUpdate(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback) {
        super(endPoint, errorHandler, callback);
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
