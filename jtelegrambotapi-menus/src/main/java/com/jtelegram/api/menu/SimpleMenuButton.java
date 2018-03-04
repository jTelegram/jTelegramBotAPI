package com.jtelegram.api.menu;

import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import lombok.Builder;

import java.util.function.BiFunction;

public class SimpleMenuButton extends MenuButton {
    private String label;
    private Menu menu;
    private BiFunction<SimpleMenuButton, CallbackQueryEvent, Boolean> onPress;

    @Builder
    private SimpleMenuButton(String label, BiFunction<SimpleMenuButton, CallbackQueryEvent, Boolean> onPress) {
        this.label = label;
        this.onPress = onPress;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setString(String label) {
        this.label = label;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean onPress(CallbackQueryEvent event) {
        return onPress.apply(this, event);
    }
}
