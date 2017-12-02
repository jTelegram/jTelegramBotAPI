package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.user.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A functional interface allowing easy handling of button clicks.
 */
@FunctionalInterface
public interface OnClickHandler {

    /**
     * Handles a button click.
     *
     * @param bot The bot instance owning this button's menu
     * @param menu The menu owning this button
     * @param message The message where the button was clicked
     * @param user The user who clicked the button
     *
     * @return The response of clicking this button
     */
    @Nullable
    MenuButtonResponse onClick(@Nonnull TelegramBot bot, @Nonnull Menu menu, @Nonnull TextMessage message, @Nonnull User user);

}
