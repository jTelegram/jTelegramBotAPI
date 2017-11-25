package io.jtelegram.api.message.impl.service;

import io.jtelegram.api.message.Message;
import lombok.Getter;

@Getter
public class PinnedMessageMessage extends ServiceMessage {
    private Message pinnedMessage;
}
