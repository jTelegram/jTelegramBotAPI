package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.impl.MenuHandler;
import com.jtelegram.api.menu.impl.MenuImpl;
import java.util.Objects;
import javax.annotation.Nonnull;

/**
 * Bootstraps the creation of menus.
 */
public class MenuLoader {

    /**
     * Registers the menu listener to a given bot.
     *
     * @param bot The bot instance
     */
    public static void registerListeners(@Nonnull TelegramBot bot) {
        bot.getEventRegistry().registerEvent(CallbackQueryEvent.class, MenuHandler::handleEvent);
    }

    /**
     * Creates a new menu, ready for use.
     *
     * @param bot The bot that owns this menu
     * @param loadingMessage The message to display while loading the first screen
     *
     * @return The new menu
     */
    @Nonnull
    public static Menu createMenu(@Nonnull TelegramBot bot, @Nonnull String loadingMessage) {
        Objects.requireNonNull(bot);
        return new MenuImpl(bot, loadingMessage);
    }

}
