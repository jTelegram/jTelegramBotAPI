package com.jtelegram.api.menu.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.inline.keyboard.InlineKeyboardButton;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuButtonResponse;
import com.jtelegram.api.menu.MenuContext;
import com.jtelegram.api.menu.MenuState;
import com.jtelegram.api.menu.OnClickHandler;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.user.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuImpl implements Menu {

    private static final AtomicInteger menuIdCounter = new AtomicInteger(0);
    private static final Set<Integer> messages = new HashSet<>();
    private static final Map<Integer, MenuImpl> menusById = new HashMap<>();

    public static void handleEvent(CallbackQueryEvent event) {
        if (!messages.contains(event.getQuery().getMessage().getMessageId())) {
            return;
        }
        TelegramBot bot = event.getBot();
        TextMessage message = (TextMessage) event.getQuery().getMessage();
        User user = event.getQuery().getFrom();
        String[] callbackData = event.getQuery().getData().split(" ");
        int menuId, stateId, row, col;
        try {
            menuId = Integer.parseInt(callbackData[0], 36);
            stateId = Integer.parseInt(callbackData[1], 36);
            row = Integer.parseInt(callbackData[2], 36);
            col = Integer.parseInt(callbackData[3], 36);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return; // invalid format, best not to try
        }
        MenuImpl menu = menusById.get(menuId);
        if (menu == null)
            return;
        if (menu.getState().stateId != stateId) {
            // user clicking an outdated menu
            // tell them, and update it
            bot.perform(AnswerCallbackQuery.builder()
                    .queryId(event.getQuery().getId())
                    .text("The menu you clicked on is out of date!\nI'm updating it now for you.")
                    .showAlert(true)
                    .build());
            menu.update(bot, message);
            return;
        }
        MenuGridImpl grid = menu.getState().getGrid();
        MenuButtonResponse response;
        try {
            MenuButton button = grid.getButton(row, col);
            response = button.onClick(bot, menu, message, user);
        } catch (Exception ignored) {
            // best to ignore
            return;
        }
        if (response != null) {
            bot.perform(AnswerCallbackQuery.builder()
                    .queryId(event.getQuery().getId())
                    .text(response.getText())
                    .showAlert(response.isShowAlert())
                    .cacheTime(response.getCacheTime())
                    .url(response.getURL() != null ? response.getURL().toString() : null)
                    .build());
        }
        menu.update(bot, message);
    }

    private final int menuId;
    private final MenuContext context;
    private final MenuStateMemoryImpl stateMemory;
    private final String loadingMessage;

    public MenuImpl(@Nonnull String loadingMessage, @Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        this.menuId = MenuImpl.menuIdCounter.get();
        this.context = new MenuContext();
        this.stateMemory = new MenuStateMemoryImpl(this, new MenuStateImpl(textSupplier, parseMode));
        this.loadingMessage = loadingMessage;

        MenuImpl.menusById.put(this.menuId, this);
    }

    @Nonnull
    @Override
    public MenuContext getContext() {
        return this.context;
    }

    @Nonnull
    @Override
    public String getLoadingMessage() {
        return this.loadingMessage;
    }

    @Nonnull
    @Override
    public MenuStateImpl getState() {
        return this.stateMemory.peekState();
    }

    @Nonnull
    @Override
    public MenuState createState(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        return new MenuStateImpl(textSupplier, parseMode);
    }

    @Nonnull
    @Override
    public MenuButton createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nonnull OnClickHandler onClickHandler) {
        return new MenuButtonImpl(buttonCreator, onClickHandler);
    }

    @Nonnull
    @Override
    public MenuStateMemoryImpl getStateMemory() {
        return this.stateMemory;
    }

    @Nonnull
    @Override
    public TextMessage send(@Nonnull TelegramBot bot, @Nonnull Chat chat, @Nullable Integer replyToMessageId) throws TelegramException {
        AtomicReference<Object> messageOrError = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        bot.perform(SendText.builder()
                .chatId(ChatId.of(chat))
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
        Object moe = messageOrError.get();
        if (moe instanceof TextMessage) {
            TextMessage message = (TextMessage) moe;
            messages.add(message.getMessageId());
            MenuStateImpl state = getState();
            bot.perform(EditTextMessage.builder()
                    .text(state.getText())
                    .parseMode(state.getParseMode())
                    .replyMarkup(state.getGrid().toReplyMarkup(this.menuId, getState().stateId))
                    .build());
            return message;
        }
        throw (TelegramException) moe;
    }

    @Override
    public void update(@Nonnull TelegramBot bot, @Nonnull TextMessage message) {
        if (message.getSender().getId() != bot.getBotInfo().getId()) {
            throw new IllegalArgumentException("Message was not sent by me!");
        }
        MenuStateImpl state = getState();
        bot.perform(EditTextMessage.builder()
                .chatId(ChatId.of(message))
                .messageId(message.getMessageId())
                .text(state.getText())
                .parseMode(state.getParseMode())
                .replyMarkup(state.getGrid().toReplyMarkup(this.menuId, state.stateId))
                .build());
    }

}
