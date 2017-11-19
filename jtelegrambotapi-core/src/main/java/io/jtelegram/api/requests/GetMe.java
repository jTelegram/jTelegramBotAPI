package io.jtelegram.api.requests;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import io.jtelegram.api.user.User;
import lombok.Builder;

import java.util.function.Consumer;

public class GetMe extends QueryTelegramRequest<User> {
    @Builder
    public GetMe(Consumer<User> callback, Consumer<TelegramException> errorHandler) {
        super("getMe", User.class, callback, errorHandler);
    }

}
