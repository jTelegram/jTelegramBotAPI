package com.jtelegram.api.events.location;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.message.media.Location;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LocationUpdateEvent extends UpdateEvent<Update.EditedMessageUpdate> {
    private Location location;
    private LocationMessage message;

    public LocationUpdateEvent(TelegramBot bot, Update.EditedMessageUpdate update, LocationMessage message) {
        super(bot, update);
        this.location = message.getLocation();
        this.message = message;
    }
}
