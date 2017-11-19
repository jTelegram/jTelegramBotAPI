package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.VenueMessage;

public class VenueMessageEvent extends MessageEvent<VenueMessage> {
    public VenueMessageEvent(TelegramBot bot, VenueMessage message) {
        super(bot, message);
    }
}
