package io.jtelegram.api.requests.framework;

import io.jtelegram.api.ex.TelegramException;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Not to be confused with Telegram Updates,
 * this type of request doesn't have a result
 * and merely just notifies the developer that
 * the request was successful or there was an error.
 *
 */
public abstract class UpdateTelegramRequest extends AbstractTelegramRequest {
    private Runnable callback;

    public UpdateTelegramRequest(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback) {
        super(endPoint, errorHandler);
        this.callback = callback;
    }

    @Override
    public void handleResponse(Response response) throws IOException {
        String body = getBody(response);

        if (body != null && validate(body) != null) {
            callback.run();
        }
    }

    /**
     * Checks validity of this request. Internal method used before sending.
     *
     * @return
     */
    protected abstract boolean isValid();
}
