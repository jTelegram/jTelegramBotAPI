package com.jtelegram.api.menu.impl;

import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.menu.BoundMenu;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuButtonResponse;
import com.jtelegram.api.menu.OnClickHandler;
import com.jtelegram.api.user.User;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuButtonImpl implements MenuButton {

    private final Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator;
    private final OnClickHandler onClickHandler;

    public MenuButtonImpl(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nonnull OnClickHandler onClickHandler) {
        this.buttonCreator = buttonCreator;
        this.onClickHandler = onClickHandler;
    }

    @Nonnull
    @Override
    public InlineKeyboardButton.InlineKeyboardButtonBuilder toButtonBuilder() {
        InlineKeyboardButton.InlineKeyboardButtonBuilder builder = InlineKeyboardButton.builder();
        buttonCreator.accept(builder);
        return builder;
    }

    @Nullable
    @Override
    public MenuButtonResponse onClick(@Nonnull BoundMenu menu, @Nonnull User user) {
        return onClickHandler.onClick(menu, user);
    }

}
