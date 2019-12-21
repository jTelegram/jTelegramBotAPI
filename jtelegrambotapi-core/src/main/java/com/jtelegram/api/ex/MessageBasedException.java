package com.jtelegram.api.ex;

public abstract class MessageBasedException extends TelegramException {
    public MessageBasedException(String message) {
        super(message);
    }

    @Override
    public String getDescription() {
        return getMessage();
    }
}
