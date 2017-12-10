package com.jtelegram.api.menu;

import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.user.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a single button on a single screen of a menu.
 */
public interface MenuButton {

    /**
     * Gets a button builder based off how this button should be represented.
     *
     * @return The button builder
     */
    @Nonnull
    InlineKeyboardButton.InlineKeyboardButtonBuilder toButtonBuilder();

    /**
     * Handles a click on this button by a user.
     *
     * @param menu The menu this button belongs to
     * @param user The user who clicked the button
     *
     * @return The response of clicking this button
     */
    @Nullable
    MenuButtonResponse onClick(@Nonnull BoundMenu menu, @Nonnull User user);

}
