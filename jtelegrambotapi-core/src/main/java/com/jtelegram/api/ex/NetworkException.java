package com.jtelegram.api.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString(callSuper = true)
public class NetworkException extends TelegramException {
    private final IOException underlyingException;
}
