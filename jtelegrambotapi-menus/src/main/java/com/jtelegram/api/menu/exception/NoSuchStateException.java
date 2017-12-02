package com.jtelegram.api.menu.exception;

/**
 * Thrown when a state doesn't exist.
 */
public class NoSuchStateException extends Exception {

    /**
     * Creates a new exception.
     */
    public NoSuchStateException() {
        super("State does not exist");
    }

}
