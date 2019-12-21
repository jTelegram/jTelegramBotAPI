package com.jtelegram.api.ex;

import lombok.ToString;

@ToString(callSuper = true)
public class EventException extends MessageBasedException {
    public EventException(String message) {
        super(message);
    }
}
