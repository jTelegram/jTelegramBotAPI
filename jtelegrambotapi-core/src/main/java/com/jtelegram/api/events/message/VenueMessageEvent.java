package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VenueMessage;

public class VenueMessageEvent extends MessageEvent<VenueMessage> {
    public VenueMessageEvent(TelegramBot bot, VenueMessage message) {
        super(bot, message);
    }
}
