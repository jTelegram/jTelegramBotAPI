package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class LocationMessageEvent extends MessageEvent<LocationMessage> {
    public LocationMessageEvent(TelegramBot bot, Update.MessageUpdate update, LocationMessage message) {
        super(bot, update, message);
    }
}
