package com.jtelegram.api.message.impl.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class MigrateFromChatIdMessage extends ServiceMessage {
    private long migrateFromChatId;
}
