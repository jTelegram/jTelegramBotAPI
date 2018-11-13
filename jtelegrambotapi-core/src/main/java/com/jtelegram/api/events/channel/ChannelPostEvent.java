package com.jtelegram.api.events.channel;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

/**
 * When a message to a channel has been posted
 */
@Getter
@ToString(callSuper = true)
public class ChannelPostEvent extends UpdateEvent<Update.ChannelPostUpdate> {
    private Message post;

    public ChannelPostEvent(TelegramBot bot, Update.ChannelPostUpdate update) {
        super(bot, update);
        this.post = update.getChannelPost();
    }
}
