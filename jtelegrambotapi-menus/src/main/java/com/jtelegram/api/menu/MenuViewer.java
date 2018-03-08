package com.jtelegram.api.menu;

import com.jtelegram.api.ex.TelegramException;

import java.util.function.Consumer;

public interface MenuViewer {
    void sendMenu(Menu menu);
}
