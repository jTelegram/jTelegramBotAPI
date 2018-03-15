package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.impl.service.ServiceMessage;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class MigrateFromChatIdMessage extends ServiceMessage {
    private long migrateFromChatId;
}
