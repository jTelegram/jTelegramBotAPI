package io.jtelegram.api.message.media;

import lombok.Getter;

@Getter
public class Venue extends SendableMedium {
    private Location location;
    private String title;
    private String address;
    private String foursquareId;

    public String getName() {
        return title;
    }
}
