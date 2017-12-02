package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.user.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a single button on a single state of a menu.
 */
public interface MenuButton {

    /**
     * A default back button to be used by anyone to go back a menu layer.
     */
    MenuButton BACK_BUTTON = new BackButton();

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
     * @param bot The bot instance owning the button
     * @param menu The menu this button belongs to
     * @param message The message on which this button was clicked
     * @param user The user who clicked the button
     *
     * @return The response of clicking this button
     */
    @Nullable
    MenuButtonResponse onClick(@Nonnull TelegramBot bot, @Nonnull Menu menu, @Nonnull TextMessage message, @Nonnull User user);

}
