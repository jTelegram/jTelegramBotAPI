package com.jtelegram.api.events;

import com.jtelegram.api.TelegramBot;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EventRegistry
 * This class handles the registration of events and the handler that accompanies it.
 * Each TelegramBot instance SHOULD have its own instance of this EventRegistry
 */
@RequiredArgsConstructor
public class EventRegistry {
    private final TelegramBot bot;

    private Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers = new HashMap<>();

    public <E extends Event> void registerEvent(Class<E> eventType, EventHandler<E> handler) {
        handlers.compute(eventType, (clazz, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(handler);
            return list;
        });
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void dispatch(E event) {
        if (event == null) {
            return;
        }

        List<EventHandler<? extends Event>> h = handlers.get(event.getType());

        if (h != null) {
            h.forEach(handler -> {
                EventHandler<E> eh = (EventHandler<E>) handler;
                eh.onEvent(event);
            });
        }
    }
}
