package com.jtelegram.api.ex;

import com.jtelegram.api.requests.framework.ResponseParameters;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TelegramApiException extends TelegramException {
    private int errorCode;
    private String description;
    private ResponseParameters parameters;

    @Override
    public String getMessage() {
        return description;
    }
}
