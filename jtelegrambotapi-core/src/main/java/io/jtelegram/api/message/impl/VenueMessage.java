package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.media.Venue;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VenueMessage extends Message<Venue> {
    private Venue venue;

    @Override
    public Venue getContent() {
        return venue;
    }
}
