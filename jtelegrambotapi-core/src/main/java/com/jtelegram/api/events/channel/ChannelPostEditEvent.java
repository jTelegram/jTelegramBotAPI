package com.jtelegram.api.events.channel;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.UpdateEvent;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.update.Update;
import lombok.Getter;
import lombok.ToString;

/**
 * When a channel post has been edited
 */
@Getter
@ToString(callSuper = true)
public class ChannelPostEditEvent extends UpdateEvent<Update.EditedChannelPostUpdate> {
    private Message post;

    public ChannelPostEditEvent(TelegramBot bot, Update.EditedChannelPostUpdate update) {
        super(bot, update);
        this.post = update.getEditedChannelPost();
    }
}
