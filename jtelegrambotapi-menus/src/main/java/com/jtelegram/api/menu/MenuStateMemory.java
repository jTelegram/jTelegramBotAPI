package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.menu.exception.NoSuchStateException;
import com.jtelegram.api.menu.exception.StateMemoryDepletedException;
import com.jtelegram.api.message.impl.TextMessage;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * Handles the current and previous states of a menu.
 */
public interface MenuStateMemory {

    /**
     * The default capacity of a {@link MenuStateMemory}
     */
    int DEFAULT_CAPACITY = 30;

    /**
     * Gets this state memory's capacity.
     *
     * @return The capacity
     */
    int getCapacity();

    /**
     * Sets this state memory's capacity.
     *
     * @param capacity The new capacity; must be &gt;= 1
     */
    void setCapacity(int capacity);

    /**
     * Gets how many states currently exist in this state memory.
     *
     * @return The number of states
     */
    int getSize();

    /**
     * Adds a new state to this state memory. Does NOT automatically update the menu.
     *
     * @param menuState The new state
     *
     * @see Menu#update(TelegramBot, TextMessage)
     */
    void pushState(@Nonnull MenuState menuState);

    /**
     * Gets the state {@code stepsBack} steps back "in time".<br>
     * e.g. {@code peekState(0) == peekState()}
     *
     * @param stepsBack The amount of steps back to go.
     *
     * @return The state
     *
     * @throws IllegalArgumentException If {@code stepsBack < 0}
     * @throws NoSuchStateException If there aren't {@code stepsBack >= getSize()}
     */
    @Nonnull
    MenuState peekState(int stepsBack) throws NoSuchStateException;

    /**
     * Gets the current state of the menu.
     *
     * @return The current state
     */
    @Nonnull
    MenuState peekState();

    /**
     * Removes the current state, and sets the previous state as the new state.
     *
     * @return The old state.
     *
     * @throws StateMemoryDepletedException If there are no previous states.
     */
    @Nonnull
    MenuState popState() throws StateMemoryDepletedException;

    /**
     * Deletes {@code count} states, going that far back "in time".
     *
     * @param count The number of states to delete
     *
     * @return The states deleted
     *
     * @throws StateMemoryDepletedException If {@code count >= getSize()}
     */
    @Nonnull
    List<? extends MenuState> popState(int count) throws StateMemoryDepletedException; // max: getSize() - 1

}
