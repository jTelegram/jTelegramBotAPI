package com.jtelegram.api.menu.old;

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
     * @param menu The menu owning this button
     * @param user The user who clicked the button
     *
     * @return The response of clicking this button
     */
    @Nullable
    MenuButtonResponse onClick(@Nonnull BoundMenu menu, @Nonnull User user);

}
