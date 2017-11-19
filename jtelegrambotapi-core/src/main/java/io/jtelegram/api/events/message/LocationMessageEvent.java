package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.LocationMessage;

public class LocationMessageEvent extends MessageEvent<LocationMessage> {
    public LocationMessageEvent(TelegramBot bot, LocationMessage message) {
        super(bot, message);
    }
}
