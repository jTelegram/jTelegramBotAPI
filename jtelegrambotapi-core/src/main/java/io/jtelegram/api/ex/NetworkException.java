package io.jtelegram.api.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

@AllArgsConstructor
@Getter
public class NetworkException extends TelegramException {
    private final IOException underlyingException;
}
