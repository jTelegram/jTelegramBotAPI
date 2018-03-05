package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.keyboard.InlineKeyboardRow;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public abstract class Menu {
    private static final String DATA_SEPARATOR = "||";
    private UUID id;
    private List<MenuViewer> viewers;
    private TelegramBot bot;

    Menu() {
        id = UUID.randomUUID();
    }

    public abstract List<MenuRow> getRows();

    public void update() {
        viewers.forEach((viewer) -> viewer.sendMenu(this));
    }

    public void addViewer(MenuViewer viewer) {
        viewers.add(viewer);
    }

    public InlineKeyboardMarkup toKeyboard() {
        List<MenuRow> rows = getRows();
        List<InlineKeyboardRow> inlineRows = new ArrayList<>(rows.size());

        for (int i = 0; i < rows.size(); i++) {
            MenuRow row = rows.get(i);
            InlineKeyboardRow.InlineKeyboardRowBuilder rowBuilder = InlineKeyboardRow.builder();

            for (int j = 0; j < row.getButtons().size(); j++) {
                MenuButton button = row.getButtons().get(j);

                rowBuilder.button(InlineKeyboardButton.builder()
                        .label(button.getLabel())
                        .callbackData(id.toString() + DATA_SEPARATOR + i + DATA_SEPARATOR + j)
                        .build());
            }

            inlineRows.add(rowBuilder.build());
        }

        return InlineKeyboardMarkup.builder()
                .inlineKeyboard(inlineRows)
                .build();
    }
}
