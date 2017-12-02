package com.jtelegram.api.menu.exception;

/**
 * Thrown when too many states are trying to be removed.
 * (State memories must retain at least one state.)
 */
public class StateMemoryDepletedException extends Exception {

    /**
     * Creates a new exception.
     */
    public StateMemoryDepletedException() {
        super("Attempting to remove the final MenuState is prohibited");
    }

}
