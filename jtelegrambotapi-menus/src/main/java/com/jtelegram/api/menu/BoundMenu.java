package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
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
     * Gets the message that this bound menu has been sent in.
     *
     * @return The message
     */
    @Nonnull
    TextMessage getMessage();

    /**
     * Updates a given message with the latest screen from this menu.
     * This is done automatically when a user clicks on a button, and
     * when they click on an out-of-date menu.
     *
     * @param bot The bot instance
     */
    void update(@Nonnull TelegramBot bot);

}
