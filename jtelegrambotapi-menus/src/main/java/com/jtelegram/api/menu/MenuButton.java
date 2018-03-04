package com.jtelegram.api.menu;

import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;

public abstract class MenuButton {
    public abstract String getLabel();

    public abstract Menu getMenu();

    public abstract boolean onPress(CallbackQueryEvent event);
}
