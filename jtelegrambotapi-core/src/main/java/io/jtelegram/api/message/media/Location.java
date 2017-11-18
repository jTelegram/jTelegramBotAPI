package io.jtelegram.api.message.media;

import lombok.Getter;

@Getter
public class Location extends SendableMedium {
    private float longitude;
    private float latitude;
}
