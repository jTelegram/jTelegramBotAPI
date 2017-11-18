package io.jtelegram.api.update.types;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.update.Update;
import lombok.Getter;

@Getter
public class ChannelPostUpdate extends Update {
    private Message channelPost;
}
