package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.menu.exception.StateMemoryDepletedException;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.user.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Goes back to the previous menu.
 */
public class BackButton implements MenuButton {

    @Nonnull
    @Override
    public InlineKeyboardButton.InlineKeyboardButtonBuilder toButtonBuilder() {
        return InlineKeyboardButton.builder().label("← Back");
    }

    @Nullable
    @Override
    public MenuButtonResponse onClick(@Nonnull TelegramBot bot, @Nonnull Menu menu, @Nonnull TextMessage message, @Nonnull User user) {
        try {
            menu.getStateMemory().popState();
            menu.update(bot, message);
            return null;
        } catch (StateMemoryDepletedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
