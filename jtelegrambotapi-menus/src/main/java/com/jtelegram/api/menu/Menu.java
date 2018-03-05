package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Menu {
    private List<MenuViewer> viewers;
    private TelegramBot bot;

    public abstract List<MenuRow> getRows();

    public void update() {
        viewers.forEach((viewer) -> viewer.sendMenu(this));
    }

    public InlineKeyboardMarkup toKeyboard() {
        return null; // todo implement this
    }
}
