package com.jtelegram.api.requests.framework;

import com.google.gson.JsonElement;
import com.jtelegram.api.ex.TelegramException;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * A generic telegram request class which
 * has a response beyond "OK"
 */
@EqualsAndHashCode(callSuper = true)
public abstract class QueryTelegramRequest<T> extends AbstractTelegramRequest {
    @Setter
    private Consumer<T> callback;
    private transient final Class<T> callbackType;

    protected QueryTelegramRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler) {
        super(endPoint, errorHandler);
        this.callback = callback;
        this.callbackType = callbackType;
    }

    @Override
    public void handleResponse(Response response) throws IOException {
        String body = getBody(response);
        JsonElement result;

        if (body != null && (result = validate(body)) != null) {
            if (callback != null) {
                try {
                    callback.accept(gson.fromJson(result.toString(), callbackType));
                } catch (TelegramException ex) {
                    handleError(ex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Checks validity of this request. Internal method used before sending.
     *
     * @return If it's valid
     */
    protected abstract boolean isValid();
}
