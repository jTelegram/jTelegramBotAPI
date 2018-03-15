package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Venue extends SendableMedium {
    private Location location;
    private String title;
    private String address;
    private String foursquareId;

    public String getName() {
        return title;
    }
}
