package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.TelegramBot;
import lombok.ToString;

@ToString(callSuper = true)
public class LocationMessageEvent extends MessageEvent<LocationMessage> {
    public LocationMessageEvent(TelegramBot bot, LocationMessage message) {
        super(bot, message);
    }
}
