package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.media.Location;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LocationMessage extends Message<Location> {
    private Location location;

    @Override
    public Location getContent() {
        return location;
    }
}
