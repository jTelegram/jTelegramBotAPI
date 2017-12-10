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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuImpl implements Menu {

    private static final Map<Long, Map<Integer, BoundMenuImpl>> menusByChatMessageId = new HashMap<>();

    public static void handleEvent(CallbackQueryEvent event) {
        Map<Integer, BoundMenuImpl> menus = menusByChatMessageId.get(event.getQuery().getMessage().getChat().getId());
        if (menus == null) {
            return;
        }
        BoundMenuImpl boundMenu = menus.get(event.getQuery().getMessage().getMessageId());
        if (boundMenu == null) {
            return;
        }
        TelegramBot bot = event.getBot();
        User user = event.getQuery().getFrom();
        String[] callbackData = event.getQuery().getData().split(" ");
        int stateId, row, col;
        try {
            stateId = Integer.parseInt(callbackData[0], 36);
            row = Integer.parseInt(callbackData[1], 36);
            col = Integer.parseInt(callbackData[2], 36);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return; // invalid format, best not to try
        }
        // shouldn't happen
        MenuStateImpl state = boundMenu.getMenu().getState();
        if (state.stateId != stateId) {
            // user clicking an outdated menu
            // tell them, and update it
            bot.perform(AnswerCallbackQuery.builder()
                    .queryId(event.getQuery().getId())
                    .text("The menu you clicked on is out of date!\nI'm updating it now for you.")
                    .showAlert(true)
                    .build());
            boundMenu.update(bot);
            return;
        }
        boolean canUse = boundMenu.getMenu().userPredicates.isEmpty(); // if no registered predicates, allow anyone
        for (Predicate<User> userPredicate : boundMenu.getMenu().userPredicates) {
            if (userPredicate.test(user)) {
                canUse = true;
                break;
            }
        }
        if (!canUse) {
            return;
        }
        MenuGridImpl grid = state.getGrid();
        MenuButtonResponse response;
        try {
            MenuButton button = grid.getButton(row, col);
            response = button.onClick(bot, boundMenu, user);
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
        boundMenu.update(bot);
    }

    @Nonnull
    private final String loadingMessage;

    @Nonnull
    private final MenuStateImpl initialState;

    @Nonnull
    private final MenuContext context;

    @Nonnull
    private MenuStateImpl currentState;

    private List<Predicate<User>> userPredicates = new ArrayList<>();

    public MenuImpl(@Nonnull String loadingMessage, @Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        this.loadingMessage = loadingMessage;
        this.initialState = new MenuStateImpl(textSupplier, parseMode);
        this.currentState = this.initialState;
        this.context = new MenuContext();
    }

    @Nonnull
    @Override
    public String getLoadingMessage() {
        return this.loadingMessage;
    }

    @Nonnull
    @Override
    public MenuStateImpl getInitialState() {
        return this.initialState;
    }

    @Nonnull
    @Override
    public MenuStateImpl getState() {
        return this.currentState;
    }

    @Override
    public void setState(@Nonnull MenuState state) {
        this.currentState = (MenuStateImpl) state;
    }

    @Nonnull
    @Override
    public MenuContext getContext() {
        return this.context;
    }

    @Override
    public void addUserPredicate(@Nonnull Predicate<User> userPredicate) {
        this.userPredicates.add(userPredicate);
    }

    @Nonnull
    @Override
    public MenuStateImpl createState(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        return new MenuStateImpl(textSupplier, parseMode);
    }

    @Nonnull
    @Override
    public MenuButtonImpl createButton(@Nonnull Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> buttonCreator, @Nonnull OnClickHandler onClickHandler) {
        return new MenuButtonImpl(buttonCreator, onClickHandler);
    }

    @Nonnull
    @Override
    public BoundMenuImpl send(@Nonnull TelegramBot bot, @Nonnull Chat chat, @Nullable Integer replyToMessageId) throws TelegramException {
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
        try {
            countDownLatch.await();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Object moe = messageOrError.get();
        if (moe instanceof TextMessage) {
            TextMessage message = (TextMessage) moe;
            BoundMenuImpl boundMenu = new BoundMenuImpl(this, message);
            Map<Integer, BoundMenuImpl> menus = menusByChatMessageId.computeIfAbsent(message.getChat().getId(), l -> new HashMap<>());
            menus.put(message.getMessageId(), boundMenu);
            menusByChatMessageId.put(message.getChat().getId(), menus);
            MenuStateImpl state = getInitialState();
            bot.perform(EditTextMessage.builder()
                    .chatId(ChatId.of(chat))
                    .messageId(message.getMessageId())
                    .text(state.getText())
                    .parseMode(state.getParseMode())
                    .replyMarkup(state.getGrid().toReplyMarkup(state.stateId))
                    .errorHandler(Throwable::printStackTrace)
                    .build());
            return boundMenu;
        }
        throw (TelegramException) moe;
    }

}