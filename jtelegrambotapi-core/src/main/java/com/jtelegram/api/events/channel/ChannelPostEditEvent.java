package com.jtelegram.api.events.channel;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.message.Message;
import lombok.Getter;
import lombok.ToString;

/**
 * When a channel post has been edited
 */
@Getter
@ToString
public class ChannelPostEditEvent extends Event {
    private Message post;

    public ChannelPostEditEvent(TelegramBot bot, Message post) {
        super(bot);
        this.post = post;
    }
}
