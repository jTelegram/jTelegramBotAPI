package io.jtelegram.api.update.types;

import io.jtelegram.api.chat.Message;
import io.jtelegram.api.update.Update;
import lombok.Getter;

@Getter
public class ChannelPostUpdate extends Update {
    private Message channelPost;
}
