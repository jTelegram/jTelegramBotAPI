package io.jtelegram.api.ex;

import lombok.Getter;

@Getter
public class TelegramException extends Exception {
    private int errorCode;
    private String description;
}
