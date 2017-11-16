package io.jtelegram.api.events;

public interface EventHandler<E extends Event> {

    void onEvent(E event);
}
