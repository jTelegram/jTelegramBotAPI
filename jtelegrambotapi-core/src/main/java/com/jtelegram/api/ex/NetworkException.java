package com.jtelegram.api.ex;

import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

@Getter
@ToString(callSuper = true)
public class NetworkException extends MessageBasedException {
    private final IOException underlyingException;

    public NetworkException(IOException underlyingException) {
        super(underlyingException.getMessage());
        this.underlyingException = underlyingException;
    }
}
