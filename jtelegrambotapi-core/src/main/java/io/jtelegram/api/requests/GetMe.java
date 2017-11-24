package io.jtelegram.api.requests;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import io.jtelegram.api.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetMe extends QueryTelegramRequest<User> {
    @Builder
    public GetMe(Consumer<User> callback, Consumer<TelegramException> errorHandler) {
        super("getMe", User.class, callback, errorHandler);
    }

    @Override
    protected boolean isValid() {
        return true;
    }
}
