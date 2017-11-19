package io.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Location extends SendableMedium {
    private float longitude;
    private float latitude;
}
