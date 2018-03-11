package com.jtelegram.api.events;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.requests.inline.AnswerInlineQuery;
import com.jtelegram.api.util.ExceptionThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * EventRegistry
 * This class handles the registration of events and the handler that accompanies it.
 * Each TelegramBot instance SHOULD have its own instance of this EventRegistry
 */
public class EventRegistry {
    private final ThreadFactory factory = new ExceptionThreadFactory();
    private final TelegramBot bot;
    private final ExecutorService threadPool;

    private Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers = new ConcurrentHashMap<>();

    public EventRegistry(TelegramBot bot) {
        this.bot = bot;

        int threadCount = bot.getRegistry().getEventThreadCount();

        if (threadCount < 1) {
            this.threadPool = Executors.newCachedThreadPool(factory);
        } else if (threadCount == 1) {
            this.threadPool = Executors.newSingleThreadExecutor(factory);
        } else {
            this.threadPool = Executors.newFixedThreadPool(threadCount, factory);
        }

        this.registerEvent(ChosenInlineResultEvent.class, AnswerInlineQuery.RESULT_LISTENER);
    }

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

        this.threadPool.submit(() -> {
            List<EventHandler<? extends Event>> h = handlers.get(event.getType());

            if (h != null) {
                h.forEach(handler -> {
                    EventHandler<E> eh = (EventHandler<E>) handler;
                    eh.onEvent(event);
                });
            }
        });
    }
}
