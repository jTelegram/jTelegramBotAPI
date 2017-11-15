package io.jtelegram.api.events;

import io.jtelegram.api.TelegramBot;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HandlerList {
    private static ArrayList<HandlerList> allLists = new ArrayList<HandlerList>();

    private final Map<TelegramBot, ArrayList<RegisteredListener>> handlerslots;
    private volatile RegisteredListener[] handlers = null;


    public HandlerList() {
        handlerslots = new HashMap<>();
//        for (EventPriority o : EventPriority.values()) {
//            handlerslots.put(o, new ArrayList<RegisteredListener>());
//        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    public static void bakeAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.bake();
            }
        }
    }

    public static void unregisterAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<RegisteredListener> list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    public static void unregisterAll(TelegramBot telegramBot) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(telegramBot);
            }
        }
    }

    public static ArrayList<RegisteredListener> getRegisteredListeners(TelegramBot telegramBot) {
        ArrayList<RegisteredListener> listeners = new ArrayList<>();
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<RegisteredListener> list : h.handlerslots.values()) {
                        for (RegisteredListener listener : list) {
                            if (listener.getTelegramBot().equals(telegramBot)) {
                                listeners.add(listener);
                            }
                        }
                    }
                }
            }
        }
        return listeners;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<HandlerList>) allLists.clone();
        }
    }

    public synchronized void register(RegisteredListener listener) {
        if (handlerslots.get(listener.getTelegramBot()).contains(listener))
            throw new IllegalStateException("This listener is already registered to that telegram bot.");
        handlers = null;
        handlerslots.get(listener.getTelegramBot()).add(listener);
    }

    public void registerAll(Collection<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            register(listener);
        }
    }

    public synchronized void unregister(RegisteredListener listener) {
        if (handlerslots.get(listener.getTelegramBot()).remove(listener)) {
            handlers = null;
        }
    }

    public synchronized void unregister(TelegramBot telegramBot) {
        handlerslots.remove(telegramBot);
        handlers = null;
    }

    public synchronized void unregister(Listener listener) {
        handlerslots.values().forEach(list->list.removeIf(registeredListener -> {
            return registeredListener.getListener().equals(listener);
        }));

       handlers = null;
    }

    public synchronized void bake() {
        if (handlers != null) return; // don't re-bake when still valid
        List<RegisteredListener> entries = new ArrayList<>();

        for (Map.Entry<TelegramBot, ArrayList<RegisteredListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new RegisteredListener[entries.size()]);
    }

    public RegisteredListener[] getRegisteredListeners() {
        RegisteredListener[] handlers;
        while ((handlers = this.handlers) == null) bake(); // This prevents fringe cases of returning null
        return handlers;
    }
}
