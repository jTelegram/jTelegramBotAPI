package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.user.User;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a state-less menu.<br>
 * {@link MenuScreen Menu screens} represent "menus" in the sense of sets of buttons with a message.<br>
 * {@link BoundMenu Bound menus} represent a Menu attached to a specific message.
 */
public interface Menu {

    String DEFAULT_REFRESHING_TEXT = "The menu you clicked on is out of date!\nI'm updating it now for you.";

    /**
     * The telegram bot instance that owns this menu.
     *
     * @return The bot instance
     */
    @Nonnull
    TelegramBot getBot();

    /**
     * Gets the message to display while the menu is loading.
     *
     * @return The loading message
     */
    @Nonnull
    String getLoadingMessage();

    /**
     * Gets the text to be displayed when a user clicks an out-of-date menu. Defaults to {@value DEFAULT_REFRESHING_TEXT}
     *
     * @return The text.
     */
    @Nonnull
    String getRefreshingText();

    /**
     * Sets the text to be displayed when a user clicks an out-of-date menu. Defaults to {@value DEFAULT_REFRESHING_TEXT}
     *
     * @param refreshingText The text
     */
    void setRefreshingText(@Nonnull String refreshingText);

    /**
     * Adds a predicate to check who can interact with buttons on the menu, called prior to handling clicks.
     *
     * @param userPredicate The predicate
     */
    void addUserPredicate(@Nonnull Predicate<User> userPredicate);

    /**
     * Gets the user predicates on this menu.
     *
     * @return The user predicates
     */
    List<Predicate<User>> getUserPredicates();

    /**
     * Adds a listener that is called when the MenuContext is first initialised.
     *
     * @param menuConsumer The bound menu consumer
     */
    void addContextLoadListener(Consumer<BoundMenu> menuConsumer);

    /**
     * Creates a new screen, ready for use. It is not automatically set to this new screen,
     * you must use {@link BoundMenu#setScreen(MenuScreen)} to accomplish that.
     *
     * @param uniqueId A type 3 (user-set) UUID to uniquely identify this MenuScreen
     * @param textSupplier The supplier that generates the message text
     * @param parseMode The parse mode used for the text
     *
     * @return The new screen
     */
    @Nonnull
    MenuScreen createScreen(@Nonnull UUID uniqueId, @Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode);

    /**
     * Creates a new screen, ready for use. It is not automatically set to this new screen,
     * you must use {@link BoundMenu#setScreen(MenuScreen)} to accomplish that.
     *
     * @param textSupplier The supplier that generates the message text
     * @param parseMode The parse mode used for the text
     *
     * @return The new screen
     */
    @Nonnull
    MenuScreen createScreen(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode);

    /**
     * Creates a new button for a screen to use. The button is not automatically added.
     *
     * @param buttonCreator The consumer used to generate buttons when they are required to be made.
     * @param onClickHandler The handler for when this button is clicked.
     *
     * @return The new button
     */
    @Nonnull
    MenuButton createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nullable OnClickHandler onClickHandler);

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
