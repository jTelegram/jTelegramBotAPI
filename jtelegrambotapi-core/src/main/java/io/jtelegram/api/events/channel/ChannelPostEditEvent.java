package io.jtelegram.api.events.channel;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.message.Message;
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
