package com.jtelegram.api.message.impl.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChannelChatCreatedMessage extends ServiceMessage {
    private boolean channelChatCreated;
}
