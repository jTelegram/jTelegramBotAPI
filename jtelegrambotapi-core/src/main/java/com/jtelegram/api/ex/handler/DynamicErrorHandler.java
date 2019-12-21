package com.jtelegram.api.ex.handler;

import com.jtelegram.api.ex.TelegramException;

import java.util.*;
import java.util.function.Consumer;

/**
 * Allows for dynamic listening for Telegram Exceptions.
 *
 * If you register a handler under a given class, it will also listen
 * for any of its subclasses.
 *
 * There can be an arbitrary amount of listeners for a given class.
 *
 * Do not modify the handlers field directly.
 */
public class DynamicErrorHandler implements Consumer<TelegramException> {
    private Map<Class<? extends TelegramException>, List<Consumer<? extends TelegramException>>> handlers = new HashMap<>();

    private DynamicErrorHandler() {
    }

    public static DynamicErrorHandler create() {
        return new DynamicErrorHandler();
    }

    public <T extends TelegramException> DynamicErrorHandler when(Class<T> clazz, Consumer<T> consumer) {
        handlers.computeIfAbsent(clazz, k -> new ArrayList<>()).add(consumer);
        return this;
    }

    @Override
    public void accept(TelegramException e) {
        Class<?> clazz = e.getClass();

        // keep moving up until we've hit RuntimeException
        while ((!clazz.equals(RuntimeException.class))) {
            // if for some reason we entered a case where the
            // current class is not a subclass or equal to
            // TelegramException, break out of the loop
            // (sanity check)
            if (!TelegramException.class.isAssignableFrom(clazz)) {
                break;
            }

            rawCall(clazz.asSubclass(TelegramException.class), e);
            clazz = clazz.getSuperclass();
        }
    }

    private void rawCall(Class<? extends TelegramException> clazz, TelegramException e) {
        handlers.getOrDefault(clazz, Collections.emptyList()).forEach((consumer) ->
                ((Consumer<TelegramException>) consumer).accept(e)
        );
    }
}
