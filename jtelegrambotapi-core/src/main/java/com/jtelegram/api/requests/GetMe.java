package com.jtelegram.api.requests;

import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import com.jtelegram.api.user.User;
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
