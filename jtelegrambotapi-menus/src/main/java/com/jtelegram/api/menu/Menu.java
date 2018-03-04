package com.jtelegram.api.menu;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Menu {
    private List<MenuViewer> viewers;

    public abstract List<MenuRow> getRows();
}
