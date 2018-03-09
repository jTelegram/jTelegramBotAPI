package com.jtelegram.api.menu;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuHandler {
    private static final Map<UUID, Menu> MENUS = new ConcurrentHashMap<>();
    private static final List<TelegramBot> REGISTERED_BOTS = new CopyOnWriteArrayList<>();

    public static void unregisterMenu(Menu menu) {
        MENUS.remove(menu.getId());
    }

    public static void registerMenu(Menu menu) {
        if (!REGISTERED_BOTS.contains(menu.getBot())) {
            menu.getBot().getEventRegistry().registerEvent(CallbackQueryEvent.class, (event) -> {
                CallbackQuery query = event.getQuery();
                String[] elements = query.getData().split(Menu.DATA_SEPARATOR);
                UUID id;

                if (elements.length != 3) {
                    return; // malformed
                }

                try {
                    id = UUID.fromString(elements[0]);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return; // client sent bad data
                }

                int rowIndex;
                int buttonIndex;

                try {
                    rowIndex = Integer.parseInt(elements[1]);
                    buttonIndex = Integer.parseInt(elements[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }

                if (MENUS.containsKey(id)) {
                    Menu m = MENUS.get(id);

                    if (m.getRows().get(rowIndex)
                            .getButtons().get(buttonIndex)
                            .onPress(event)) {
                        m.update();
                    }

                    m.getBot().perform(AnswerCallbackQuery.builder()
                            .queryId(query.getId())
                            .build());
                }
            });

            REGISTERED_BOTS.add(menu.getBot());
        }

        MENUS.put(menu.getId(), menu);
        menu.update();
    }

}
