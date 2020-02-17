package com.jtelegram.api.ex.handler;

import com.jtelegram.api.ex.InvalidResponseException;
import com.jtelegram.api.ex.TelegramApiException;
import com.jtelegram.api.ex.TelegramException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicErrorHandlerTest {
    @Test
    void specific_call() {
        AtomicInteger count = new AtomicInteger(1);
        DynamicErrorHandler handler = DynamicErrorHandler.create()
                .when(InvalidResponseException.class, decrementCounter(count));

        handler.accept(new InvalidResponseException("", ""));
        assertEquals(count.get(), 0);
    }

    @Test
    void abstract_call() {
        AtomicInteger count = new AtomicInteger(1);
        DynamicErrorHandler handler = DynamicErrorHandler.create()
                .when(TelegramException.class, decrementCounter(count));

        handler.accept(new InvalidResponseException("", ""));
        assertEquals(count.get(), 0);
    }

    @Test
    void specific_not_call() {
        AtomicInteger count = new AtomicInteger(1);
        DynamicErrorHandler handler = DynamicErrorHandler.create()
                .when(InvalidResponseException.class, decrementCounter(count));

        handler.accept(new TelegramApiException());
        assertEquals(count.get(), 1);
    }

    @Test
    void combination_call() {
        AtomicInteger count = new AtomicInteger(3);
        DynamicErrorHandler handler = DynamicErrorHandler.create()
                .when(TelegramException.class, decrementCounter(count))
                .when(InvalidResponseException.class, decrementCounter(count))
                .when(TelegramApiException.class, decrementCounter(count));

        handler.accept(new InvalidResponseException("", ""));
        assertEquals(count.get(), 1);
    }

    private <T extends TelegramException> Consumer<T> decrementCounter(AtomicInteger count) {
        return (e) -> count.decrementAndGet();
    }
}
