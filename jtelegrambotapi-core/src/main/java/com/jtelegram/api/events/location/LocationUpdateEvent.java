package com.jtelegram.api.events.location;

import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.media.Location;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LocationUpdateEvent extends Event {
    private Location location;
    private LocationMessage message;

    public LocationUpdateEvent(TelegramBot bot, LocationMessage message) {
        super(bot);
        this.location = message.getLocation();
        this.message = message;
    }
}
