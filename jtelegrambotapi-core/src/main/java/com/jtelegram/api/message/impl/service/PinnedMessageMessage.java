package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.message.Message;
import lombok.Getter;

@Getter
public class PinnedMessageMessage extends ServiceMessage {
    private Message pinnedMessage;
}
