package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.media.Venue;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class VenueMessage extends Message<Venue> {
    private Venue venue;

    @Override
    public Venue getContent() {
        return venue;
    }
}
