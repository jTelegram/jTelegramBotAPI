package io.jtelegram.api.message.impl.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChannelChatCreatedMessage extends ServiceMessage {
    private boolean channelChatCreated;
}
