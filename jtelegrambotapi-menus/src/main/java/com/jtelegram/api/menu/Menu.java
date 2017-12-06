package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a state-less menu.<br>
 * {@link MenuState Menu states} represent "menus" in the sense of sets of buttons with a message.<br>
 * {@link BoundMenu Bound menus} represent a Menu attached to a specific message.
 */
public interface Menu {

    /**
     * Gets the message to display while the menu is loading.
     *
     * @return The loading message
     */
    @Nonnull
    String getLoadingMessage();

    /**
     * Gets the initial state of the menu, when it is first sent to a chat.
     *
     * @return The initial state
     */
    @Nonnull
    MenuState getInitialState();

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
     * Gets the key-value storage, or context, associated with this menu, persistent across states.
     *
     * @return The key-value storage
     */
    @Nonnull
    MenuContext getContext();

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
     * Creates a new button for a state to use. The button is not automatically added.
     *
     * @param buttonCreator The consumer used to generate buttons when they are required to be made.
     * @param onClickHandler The handler for when this button is clicked.
     *
     * @return The new button
     */
    @Nonnull
    MenuButton createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nonnull OnClickHandler onClickHandler);

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
     * @see TelegramBot#perform(com.jtelegram.api.requests.framework.TelegramRequest)
     * @see com.jtelegram.api.requests.message.send.SendText
     */
    @Nonnull
    BoundMenu send(@Nonnull TelegramBot bot, @Nonnull Chat chat, @Nullable Integer replyToMessageId) throws TelegramException;

}
