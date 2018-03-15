package com.jtelegram.api.events;

import com.jtelegram.api.TelegramBot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Event {
    @Getter
    private final TelegramBot bot;

    public Class<? extends Event> getType() {
        return getClass();
    }

}
