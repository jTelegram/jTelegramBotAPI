package io.jtelegram.api.events.location;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.impl.LocationMessage;
import io.jtelegram.api.message.media.Location;
import lombok.Getter;

@Getter
public class LocationUpdateEvent extends Event {
    private Location location;
    private LocationMessage message;

    public LocationUpdateEvent(TelegramBot bot, LocationMessage message) {
        super(bot);
        this.location = message.getLocation();
        this.message = message;
    }
}
