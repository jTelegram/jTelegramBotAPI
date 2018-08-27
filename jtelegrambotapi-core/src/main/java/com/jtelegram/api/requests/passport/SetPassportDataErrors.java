package com.jtelegram.api.requests.passport;

import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import com.jtelegram.api.requests.passport.error.PassportElementError;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString(callSuper = true)
public class SetPassportDataErrors extends UpdateTelegramRequest {
    private final Integer userId;
    private final List<PassportElementError> errors;

    public SetPassportDataErrors(Consumer<TelegramException> errorHandler, Runnable callback, Integer userId, List<PassportElementError> errors) {
        super("setPassportDataErrors", errorHandler, callback);
        this.userId = userId;
        this.errors = errors;
    }

    @Override
    protected boolean isValid() {
        return userId != null && errors != null && errors.stream().allMatch(PassportElementError::isValid);
    }
}
