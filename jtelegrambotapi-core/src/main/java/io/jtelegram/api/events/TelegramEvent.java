package io.jtelegram.api.events;

public abstract class TelegramEvent {
    private final String name;

    public TelegramEvent(String name) {
        this.name = name;
    }

    public abstract HandlerList getHandlers();

}
