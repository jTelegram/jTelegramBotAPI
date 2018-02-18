package com.jtelegram.api.menu.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.menu.BoundMenu;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuButtonResponse;
import com.jtelegram.api.menu.MenuGrid;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;
import com.jtelegram.api.user.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author Nick Robson
 */
public class MenuHandler {

    private static final Map<Long, MenuHandler> menuHandlers = new HashMap<>();

    public static MenuHandler getFor(TelegramBot bot) {
        return menuHandlers.computeIfAbsent(bot.getBotInfo().getId(), MenuHandler::new);
    }

    Map<UUID, MenuImpl> menusByMenuScreenId = new HashMap<>();
    Map<UUID, MenuScreenImpl> menuScreensByUniqueId = new HashMap<>();
    private final Map<Long, Map<Integer, BoundMenuImpl>> menusByChatMessageId = new HashMap<>();

    private MenuHandler(Long botId) {
    }

    static void registerMenu(TextMessage message, BoundMenuImpl boundMenu) {
        long chatId = message.getChat().getId();
        int messageId = message.getMessageId();

        MenuHandler handler = getFor(boundMenu.getBot());
        Map<Integer, BoundMenuImpl> menus = handler.menusByChatMessageId.computeIfAbsent(chatId, l -> new HashMap<>());
        menus.put(messageId, boundMenu);
        handler.menusByChatMessageId.put(chatId, menus);
    }

    public static void handleEvent(CallbackQueryEvent event) {
        CallbackQuery query = event.getQuery();
        String[] callbackData = query.getData().split(MenuImpl.CALLBACK_DATA_SEPARATOR);
        UUID screenId;
        int row, col;
        try {
            screenId = UUID.fromString(callbackData[0]);
            row = Integer.parseInt(callbackData[1], MenuImpl.CALLBACK_DATA_RADIX);
            col = Integer.parseInt(callbackData[2], MenuImpl.CALLBACK_DATA_RADIX);
        } catch (Exception ex) {
            return; // invalid format, best not to try
        }
        MenuHandler handler = getFor(event.getBot());
        BoundMenuImpl boundMenu = handler.getBoundMenu(event, screenId);
        if (boundMenu == null || !handler.canUse(event, boundMenu)) {
            return;
        }
        MenuScreenImpl screen = boundMenu.getScreen();
        if (!screen.getUniqueId().equals(screenId)) {
            event.getBot().perform(AnswerCallbackQuery.builder()
                    .queryId(query.getId())
                    .text(boundMenu.getMenu().getRefreshingText())
                    .showAlert(true)
                    .build());
            boundMenu.update();
            return;
        }
        User user = event.getQuery().getFrom();
        MenuButtonResponse response = handler.getResponse(boundMenu, screen, row, col, user);
        handler.sendResponse(event, response);
        boundMenu.update();
    }

    private BoundMenuImpl getBoundMenu(CallbackQueryEvent event, UUID screenId) {
        CallbackQuery query = event.getQuery();
        if (!(query.getMessage() instanceof TextMessage)) {
            return null;
        }
        TextMessage message = (TextMessage) query.getMessage();

        Map<Integer, BoundMenuImpl> menus = menusByChatMessageId.get(message.getChat().getId());
        if (menus != null) {
            BoundMenuImpl boundMenu = menus.get(message.getMessageId());
            if (boundMenu != null) {
                return boundMenu;
            }
        }

        MenuImpl menu = menusByMenuScreenId.get(screenId);
        MenuScreenImpl menuScreen = menuScreensByUniqueId.get(screenId);
        if (menu != null && menuScreen != null) {
            BoundMenuImpl boundMenu = new BoundMenuImpl(menu, message);
            registerMenu(message, boundMenu);
            boundMenu.setScreen(menuScreen);
            boundMenu.update();
            return boundMenu;
        }
        return null;
    }

    private boolean canUse(CallbackQueryEvent event, BoundMenu boundMenu) {
        User user = event.getQuery().getFrom();
        boolean canUse = true;
        for (Predicate<User> userPredicate : boundMenu.getMenu().getUserPredicates()) {
            if (!userPredicate.test(user)) {
                canUse = false;
                break;
            }
        }
        return canUse;
    }

    private MenuButtonResponse getResponse(BoundMenuImpl boundMenu, MenuScreenImpl screen, int row, int col, User user) {
        try {
            MenuGrid grid = screen.getGrid();
            MenuButton button = grid.getButton(row, col);
            return button.onClick(boundMenu, user);
        } catch (Exception ignored) {
            return null;
        }
    }

    private void sendResponse(CallbackQueryEvent event, MenuButtonResponse response) {
        if (response != null) {
            event.getBot().perform(AnswerCallbackQuery.builder()
                    .queryId(event.getQuery().getId())
                    .text(response.getText())
                    .showAlert(response.isShowAlert())
                    .cacheTime(response.getCacheTime())
                    .url(response.getURL() != null ? response.getURL().toString() : null)
                    .build());
        }
    }

}
