package com.jtelegram.api.menu;

import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.UUID;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a single screen of a menu
 */
public interface MenuScreen {

    /**
     * Gets the unique identifier of this screen.<br>
     * Type 3 if {@link Menu#createScreen(UUID, Supplier, ParseMode)} was used.<br>
     * Type 4 if {@link Menu#createScreen(Supplier, ParseMode)} was used.
     *
     * @return The unique ID
     */
    @Nonnull
    UUID getUniqueId();

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
