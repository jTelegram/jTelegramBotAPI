package com.jtelegram.api.menu;

import com.jtelegram.api.requests.message.framework.ParseMode;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a single state of a menu
 */
public interface MenuState {

    /**
     * Gets the text displayed on this state.
     *
     * @return The text
     */
    @Nonnull
    String getText();

    /**
     * Gets the parse mode of this state.
     *
     * @return The parse mode
     */
    @Nullable
    ParseMode getParseMode();

    /**
     * Gets the grid of buttons on this state.
     *
     * @return The grid of buttons
     */
    @Nonnull
    MenuGrid getGrid();

}
