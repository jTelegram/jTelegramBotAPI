package io.jtelegram.api.events;

import io.jtelegram.api.TelegramBot;

public class TestEventBlah extends Event{
    public TestEventBlah(String name, TelegramBot bot) {
        super(name, bot);
    }
}
