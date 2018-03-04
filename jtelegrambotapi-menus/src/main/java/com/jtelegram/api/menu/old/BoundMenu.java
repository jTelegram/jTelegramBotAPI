package com.jtelegram.api.menu.old;

import com.jtelegram.api.message.impl.TextMessage;
import javax.annotation.Nonnull;

/**
 * Represents a menu bound to a message
 */
public interface BoundMenu {

    /**
     * Gets the menu that this bound menu represents.
     *
     * @return The menu
     */
    @Nonnull
    Menu getMenu();

    /**
     * Gets the screen currently being displayed on the menu.
     *
     * @return The current screen
     */
    @Nonnull
    MenuScreen getScreen();

    /**
     * Sets the screen of the menu.
     *
     * @param screen The new screen.
     */
    void setScreen(@Nonnull MenuScreen screen);

    /**
     * Gets the message that this bound menu has been sent in.
     *
     * @return The message
     */
    @Nonnull
    TextMessage getMessage();

    /**
     * Gets the key-value storage, or context, associated with this menu, persistent across screens.
     *
     * @return The key-value storage
     */
    @Nonnull
    MenuContext getContext();

    /**
     * Updates this bound menu's message with the latest screen from the owning menu.
     * This is done automatically when a user clicks on a button, and when they click on an out-of-date menu.
     */
    void update();

}
