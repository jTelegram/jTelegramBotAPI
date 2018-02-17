package com.jtelegram.api.menu.impl;

import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.keyboard.InlineKeyboardRow;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuGrid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

public class MenuGridImpl implements MenuGrid {

    private final List<List<MenuButton>> grid;

    MenuGridImpl() {
        this.grid = new ArrayList<>();
    }

    @Nonnull
    @Override
    public List<MenuButton> getRow(int row) {
        return Collections.unmodifiableList(grid.get(row));
    }

    @Nonnull
    @Override
    public MenuButton getButton(int row, int column) {
        return grid.get(row).get(column);
    }

    @Override
    public void addRow(@Nonnull MenuButton... buttons) {
        grid.add(new ArrayList<>(
                Arrays.stream(buttons)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        ));
    }

    @Override
    public void addRow(int row, @Nonnull MenuButton... buttons) {
        grid.add(row, new ArrayList<>(
            Arrays.stream(buttons)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
        ));
    }

    public void addButton(int row, @Nonnull MenuButton button) {
        grid.get(row).add(button);
    }

    @Override
    public void addButton(int row, int column, @Nonnull MenuButton button) {
        grid.get(row).add(column, button);
    }

    @Override
    public void deleteRow(int row) {
        grid.remove(row);
    }

    @Override
    public void deleteButton(int row, int column) {
        grid.get(row).remove(column);
    }

    InlineKeyboardMarkup toReplyMarkup(UUID screenId) {
        String prefix = screenId.toString() + MenuImpl.CALLBACK_DATA_SEPARATOR;

        List<InlineKeyboardRow> rows = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            List<MenuButton> buttonList = this.grid.get(i);
            String rowPrefix = prefix + Integer.toString(i, MenuImpl.CALLBACK_DATA_RADIX) + MenuImpl.CALLBACK_DATA_SEPARATOR;
            for (int j = 0; j < buttonList.size(); j++) {
                MenuButton button = buttonList.get(j);
                String buttonPrefix = rowPrefix + Integer.toString(j, MenuImpl.CALLBACK_DATA_RADIX);
                row.add(button.toButtonBuilder().callbackData(buttonPrefix).build());
            }
            rows.add(InlineKeyboardRow.builder().buttons(row).build());
        }
        return InlineKeyboardMarkup.builder()
                .inlineKeyboard(rows)
                .build();
    }

}
