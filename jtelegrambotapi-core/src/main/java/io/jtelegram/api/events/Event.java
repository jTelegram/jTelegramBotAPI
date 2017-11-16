package io.jtelegram.api.events;

import io.jtelegram.api.TelegramBot;
import lombok.Getter;

public abstract class Event {
    @Getter
    private final String name;
    @Getter
    private final TelegramBot bot;

    public Event(String name, TelegramBot bot) {
        this.name = name;
        this.bot = bot;
    }

    public Class<? extends Event> getType() {
        return getClass();
    }

}
