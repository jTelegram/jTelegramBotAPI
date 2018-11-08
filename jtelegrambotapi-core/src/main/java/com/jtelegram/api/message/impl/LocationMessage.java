package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.media.Location;
import com.jtelegram.api.requests.message.edit.EditMessageLiveLocation;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LocationMessage extends Message<Location> {
    private Location location;

    @Override
    public Location getContent() {
        return location;
    }

    public EditMessageLiveLocation.EditMessageLiveLocationBuilder toEditLiveLocationRequest() {
        return EditMessageLiveLocation.forMessage(this);
    }
}
