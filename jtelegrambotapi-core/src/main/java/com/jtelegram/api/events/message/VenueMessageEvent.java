package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.VenueMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class VenueMessageEvent extends MessageEvent<VenueMessage> {
    public VenueMessageEvent(TelegramBot bot, Update.MessageUpdate update, VenueMessage message) {
        super(bot, update, message);
    }
}
