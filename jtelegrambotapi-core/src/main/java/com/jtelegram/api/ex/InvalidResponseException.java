package com.jtelegram.api.ex;

import lombok.Getter;
import lombok.ToString;

/**
 * This exception is called when the API returns
 * a non-JSON response
 */
@ToString(callSuper = true)
@Getter
public class InvalidResponseException extends MessageBasedException {
    /**
     * A description of the error which could contain
     * sensitive information (e.g. message contents)
     *
     * Separate from getMessage() in order to avoid
     * unnecessary logging of sensitive data
     */
    private String sensitiveMessage;

    public InvalidResponseException(String message, String sensitiveAddition) {
        super(message);
        this.sensitiveMessage = message + " SENSITIVE: " + sensitiveAddition;
    }
}
