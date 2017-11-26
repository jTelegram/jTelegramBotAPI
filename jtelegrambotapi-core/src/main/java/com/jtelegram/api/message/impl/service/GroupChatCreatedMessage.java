package com.jtelegram.api.message.impl.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GroupChatCreatedMessage extends ServiceMessage {
    private boolean groupChatCreated;
}
