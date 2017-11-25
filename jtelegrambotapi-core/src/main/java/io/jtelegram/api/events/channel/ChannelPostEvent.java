package io.jtelegram.api.events.channel;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.Message;
import lombok.Getter;
import lombok.ToString;

/**
 * When a message to a channel has been posted
 */
@Getter
@ToString
public class ChannelPostEvent extends Event {
    private Message post;

    public ChannelPostEvent(TelegramBot bot, Message post) {
        super(bot);
        this.post = post;
    }
}
