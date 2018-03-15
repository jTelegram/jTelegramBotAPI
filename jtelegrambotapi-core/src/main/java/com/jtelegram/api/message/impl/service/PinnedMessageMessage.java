package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.message.Message;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PinnedMessageMessage extends ServiceMessage {
    private Message pinnedMessage;
}
