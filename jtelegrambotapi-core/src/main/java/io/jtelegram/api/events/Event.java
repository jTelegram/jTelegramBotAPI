package io.jtelegram.api.events;

import io.jtelegram.api.TelegramBot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Event {
    @Getter
    private final TelegramBot bot;

    public Class<? extends Event> getType() {
        return getClass();
    }

}
