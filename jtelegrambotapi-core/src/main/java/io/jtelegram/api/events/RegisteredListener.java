package io.jtelegram.api.events;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.ex.EventException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegisteredListener {
    private final Listener listener;
    private final TelegramBot telegramBot;
    private final EventExecutor executor;

    public void callEvent(final TelegramEvent event) throws EventException {
        executor.execute(listener, event);
    }

}
