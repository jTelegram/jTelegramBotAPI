package com.jtelegram.api.menu;

import com.jtelegram.api.requests.message.framework.ParseMode;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a single screen of a menu
 */
public interface MenuScreen {

    /**
     * Gets the text displayed on this screen.
     *
     * @return The text
     */
    @Nonnull
    String getText();

    /**
     * Gets the parse mode of this screen.
     *
     * @return The parse mode
     */
    @Nullable
    ParseMode getParseMode();

    /**
     * Gets the grid of buttons on this screen.
     *
     * @return The grid of buttons
     */
    @Nonnull
    MenuGrid getGrid();

}
