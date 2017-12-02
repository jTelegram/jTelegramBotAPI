package com.jtelegram.api.menu;

import java.util.List;
import javax.annotation.Nonnull;

/**
 * Represents the layout of buttons on the menu's state.
 */
public interface MenuGrid {

    /**
     * Gets the buttons in a row.
     *
     * @param row The row
     *
     * @return The buttons
     */
    @Nonnull
    List<MenuButton> getRow(int row);

    /**
     * Gets a specific button, from its row and column.
     *
     * @param row The row
     * @param column The column
     *
     * @return The button
     */
    @Nonnull
    MenuButton getButton(int row, int column);

    /**
     * Adds a row of buttons.
     *
     * @param buttons The buttons in this row
     */
    void addRow(@Nonnull MenuButton... buttons);

    /**
     * Adds a row of buttons.
     *
     * @param row The row to insert before
     * @param buttons The buttons
     */
    void addRow(int row, @Nonnull MenuButton... buttons);

    /**
     * Adds a button to the grid.
     *
     * @param row The row to append to
     * @param button The button
     */
    void addButton(int row, @Nonnull MenuButton button);

    /**
     * Adds a button to the grid.
     *
     * @param row The row to add to
     * @param column The column to add before
     * @param button The button
     */
    void addButton(int row, int column, @Nonnull MenuButton button);

    /**
     * Deletes a row.
     *
     * @param row The row
     */
    void deleteRow(int row);

    /**
     * Deletes a specific button.
     *
     * @param row The button's row
     * @param column The button's column
     */
    void deleteButton(int row, int column);

}
