package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SimpleMenu extends Menu {
    @Getter
    private final List<MenuRow> rows;

    @Builder
    private SimpleMenu(TelegramBot bot) {
        super(bot);
        rows = new ArrayList<>();
    }

    @Override
    public Consumer<TelegramException> getExceptionConsumer() {
        return e -> {
            //ignored
        };
    }

    public void addRow(MenuRow... rows) {
        this.rows.addAll(Arrays.asList(rows));
    }

    public void setRow(int index, MenuRow row) {
        this.rows.set(index, row);
    }
}
