package com.jtelegram.api.requests.framework;

import com.google.gson.JsonElement;
import com.jtelegram.api.ex.TelegramException;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

/**
 * A generic telegram request class which
 * has a response beyond "OK"
 */
@EqualsAndHashCode(callSuper = true)
public abstract class QueryTelegramRequest<T> extends AbstractTelegramRequest<String> {
    private transient final Consumer<T> callback;
    private transient final Class<T> callbackType;

    protected QueryTelegramRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler) {
        super(endPoint, errorHandler);
        this.callback = callback;
        this.callbackType = callbackType;
    }

    @Override
    public void handleResponse(HttpResponse<String> response) throws IOException {
        String body = getBody(response);
        response.body();
        JsonElement result;

        if (body != null && (result = validate(body)) != null) {
            if (callback != null)
                callback.accept(gson.fromJson(result.toString(), callbackType));
        }
    }

    /**
     * Checks validity of this request. Internal method used before sending.
     *
     * @return If it's valid
     */
    protected abstract boolean isValid();
}
