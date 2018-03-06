package com.jtelegram.api.menu;

import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import lombok.Getter;
import lombok.Setter;

public abstract class MenuButton {
    @Getter
    @Setter
    private Menu menu;

    public abstract String getLabel();


    public abstract boolean onPress(CallbackQueryEvent event);
}
