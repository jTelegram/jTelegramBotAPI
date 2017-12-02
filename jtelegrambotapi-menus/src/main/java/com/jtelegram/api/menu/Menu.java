package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.framework.TelegramRequest;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a menu, and may have shared state across multiple messages.
 * {@link MenuState Menu states} represent different "menus" in the sense of sets of buttons with a message.
 */
public interface Menu {

    /**
     * Gets the key-value storage, or context, associated with this menu, persistent across states.
     *
     * @return The key-value storage
     */
    @Nonnull
    MenuContext getContext();

    /**
     * Gets the message to display while the menu is loading.
     *
     * @return The loading message
     */
    @Nonnull
    String getLoadingMessage();

    /**
     * Creates a new state, ready for use. It is not automatically set to this new state,
     * you must use {@code getStateMemory().pushState(state)} to accomplish that.
     *
     * @param textSupplier The supplier that generates the message text
     * @param parseMode The parse mode used for the text
     *
     * @return The new state
     */
    @Nonnull
    MenuState createState(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode);

    /**
     * Creates a new button for a state to use.
     *
     * @param buttonCreator The consumer used to generate buttons when they are required to be made.
     * @param onClickHandler The handler for when this button is clicked.
     *
     * @return The new button
     */
    @Nonnull
    MenuButton createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nonnull OnClickHandler onClickHandler);

    /**
     * Gets this menu's {@link MenuStateMemory state memory}
     *
     * @return The state memory
     */
    @Nonnull
    MenuStateMemory getStateMemory();

    /**
     * Gets the current state of the menu. Equivalent to {@code getStateMemory().peekState()}.
     *
     * @return The current state
     */
    @Nonnull
    MenuState getState();

    /**
     * Sends this menu to a new chat
     *
     * @param bot The bot instance
     * @param chat The chat
     * @param replyToMessageId The message to reply to (or null)
     *
     * @return The text message
     *
     * @throws TelegramException If an error occurred while sending it
     *
     * @see TelegramBot#perform(TelegramRequest)
     * @see com.jtelegram.api.requests.message.send.SendText
     */
    @Nonnull
    TextMessage send(@Nonnull TelegramBot bot, @Nonnull Chat chat, @Nullable Integer replyToMessageId) throws TelegramException;

    /**
     * Updates a given message with the latest state from this menu.
     * This is done automatically when a user clicks on a button, and
     * when they click on an out-of-date menu.
     *
     * @param bot The bot instance
     * @param message The message
     */
    void update(@Nonnull TelegramBot bot, @Nonnull TextMessage message);

}
