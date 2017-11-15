package io.jtelegram.api.requests;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.AbstractTelegramRequest;
import io.jtelegram.api.user.User;
import lombok.Builder;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

public class GetMe extends AbstractTelegramRequest {
    private final Consumer<User> callback;

    @Builder
    public GetMe(Consumer<TelegramException> errorHandler, Consumer<User> callback) {
        super("getMe", errorHandler);
        this.callback = callback;
    }

    @Override
    public void handleResponse(Response response) throws IOException {
        String body = getBody(response);

        if (body != null && validate(body)) {
            callback.accept(gson.fromJson(body, User.class));
        }
    }
}
