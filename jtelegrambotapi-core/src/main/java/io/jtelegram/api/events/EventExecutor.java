package io.jtelegram.api.events;


import io.jtelegram.api.ex.EventException;

public interface EventExecutor {

    void execute(Listener Listener, TelegramEvent event) throws EventException;
}
