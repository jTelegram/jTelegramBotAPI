package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.keyboard.InlineKeyboardRow;
import com.jtelegram.api.util.TextBuilder;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class Menu {
    public static final InlineKeyboardMarkup KEYBOARD_MARKUP = InlineKeyboardMarkup.builder().keyboard(InlineKeyboardRow.builder().button(InlineKeyboardButton.builder().label("Placeholder").callbackData("Placeholder").build()).build()).build();

    public static final String DATA_SEPARATOR = "&";
    private UUID id;
    private List<MenuViewer> viewers;
    private TelegramBot bot;

    protected Menu(TelegramBot bot) {
        viewers = new LinkedList<>();
        id = UUID.randomUUID();
        this.bot = bot;
    }

    public abstract List<MenuRow> getRows();

    public abstract void handleException(TelegramException exception);

    public void update() {
        viewers.forEach((viewer) -> viewer.sendMenu(this));
    }

    public void update(MenuViewer viewer) {
        viewer.sendMenu(this);
    }

    public void addViewer(MenuViewer viewer) {
        viewers.add(viewer);
        update(viewer);
    }

    public void addViewers(Collection<MenuViewer> viewers) {
        this.viewers.addAll(viewers);
        update();
    }

    public void migrateTo(Menu other) {
        other.addViewers(viewers);
        viewers.clear();
    }

    /**
     * Use this so the bot can update the message of the menu as well.
     *
     * @return
     */
    public TextBuilder getMenuMessage() {
        return null;
    }

    public InlineKeyboardMarkup toKeyboard() {
        List<MenuRow> rows = getRows();
        List<InlineKeyboardRow> inlineRows = new ArrayList<>(rows.size());

        for (int i = 0; i < rows.size(); i++) {
            MenuRow row = rows.get(i);
            InlineKeyboardRow.InlineKeyboardRowBuilder rowBuilder = InlineKeyboardRow.builder();

            for (int j = 0; j < row.getButtons().size(); j++) {
                MenuButton button = row.getButtons().get(j);
                button.setMenu(this);

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
