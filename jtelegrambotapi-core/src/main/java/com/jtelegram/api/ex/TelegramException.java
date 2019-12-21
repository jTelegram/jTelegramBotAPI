package com.jtelegram.api.ex;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class TelegramException extends RuntimeException {
    public TelegramException() {
    }

    public TelegramException(String message) {
        super(message);
    }

    public abstract String getDescription();
}
