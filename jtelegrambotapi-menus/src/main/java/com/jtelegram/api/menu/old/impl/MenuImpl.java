package com.jtelegram.api.menu.old.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.menu.old.BoundMenu;
import com.jtelegram.api.menu.old.Menu;
import com.jtelegram.api.menu.old.OnClickHandler;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.user.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MenuImpl implements Menu {

    static final String CALLBACK_DATA_SEPARATOR = ",";
    static final int CALLBACK_DATA_RADIX = 36;

    private final TelegramBot bot;
    private final String loadingMessage;
    private final MenuScreenImpl initialScreen;

    private String refreshingText = Menu.DEFAULT_REFRESHING_TEXT;
    private List<Predicate<User>> userPredicates = new ArrayList<>();
    private List<Consumer<BoundMenu>> menuConsumers = new ArrayList<>();

    public MenuImpl(@Nonnull TelegramBot bot, @Nonnull String loadingMessage) {
        this.bot = bot;
        this.loadingMessage = loadingMessage;
        this.initialScreen = new MenuScreenImpl(bot, () -> loadingMessage, ParseMode.NONE);
    }

    @Nonnull
    @Override
    public TelegramBot getBot() {
        return bot;
    }

    @Nonnull
    @Override
    public String getLoadingMessage() {
        return this.loadingMessage;
    }

    @Nonnull
    @Override
    public String getRefreshingText() {
        return refreshingText;
    }

    @Override
    public void setRefreshingText(@Nonnull String refreshingText) {
        this.refreshingText = refreshingText;
    }

    @Override
    public void addUserPredicate(@Nonnull Predicate<User> userPredicate) {
        this.userPredicates.add(userPredicate);
    }

    @Override
    public List<Predicate<User>> getUserPredicates() {
        return userPredicates;
    }

    @Override
    public void addContextLoadListener(Consumer<BoundMenu> menuConsumer) {
        this.menuConsumers.add(menuConsumer);
    }

    List<Consumer<BoundMenu>> getMenuConsumers() {
        return menuConsumers;
    }

    @Nonnull
    @Override
    public MenuScreenImpl createScreen(@Nonnull UUID uniqueId, @Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        if (uniqueId.version() != 3) {
            throw new IllegalArgumentException("UUID must be type 3 (user-set)");
        }
        MenuHandler handler = MenuHandler.getFor(this.bot);
        MenuScreenImpl menuScreen = new MenuScreenImpl(bot, uniqueId, textSupplier, parseMode);
        handler.menusByMenuScreenId.put(menuScreen.getUniqueId(), this);
        return menuScreen;
    }

    @Nonnull
    @Override
    public MenuScreenImpl createScreen(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        return new MenuScreenImpl(bot, textSupplier, parseMode);
    }

    @Nonnull
    @Override
    public MenuButtonImpl createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nullable OnClickHandler onClickHandler) {
        return new MenuButtonImpl(buttonCreator, onClickHandler);
    }

    @Nonnull
    @Override
    public BoundMenuImpl send(@Nonnull TelegramBot bot, @Nonnull Chat chat, @Nullable Integer replyToMessageId) throws TelegramException {
        return send(bot, chat.getChatId(), replyToMessageId);
    }

    @Override
    public BoundMenuImpl send(@Nonnull TelegramBot bot, @Nonnull ChatId chatId, @Nullable Integer replyToMessageId) throws TelegramException {
        AtomicReference<Object> messageOrError = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        bot.perform(SendText.builder()
                .chatId(chatId)
                .replyToMessageID(replyToMessageId)
                .text(getLoadingMessage())
                .errorHandler(e -> {
                    messageOrError.set(e);
                    countDownLatch.countDown();
                })
                .callback(m -> {
                    messageOrError.set(m);
                    countDownLatch.countDown();
                })
                .build());
        try {
            countDownLatch.await();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Object moe = messageOrError.get();
        if (moe instanceof TextMessage) {
            TextMessage message = (TextMessage) moe;
            BoundMenuImpl boundMenu = new BoundMenuImpl(this, message);
            MenuHandler.registerMenu(message, boundMenu);
            boundMenu.setScreen(initialScreen);
            return boundMenu;
        }
        throw (TelegramException) moe;
    }
}
