package com.jtelegram.api.events.message;

import com.jtelegram.api.message.impl.service.ServiceMessage;
import com.jtelegram.api.TelegramBot;
import lombok.ToString;

@ToString(callSuper = true)
public abstract class ServiceMessageEvent<T extends ServiceMessage> extends MessageEvent<T> {
    protected ServiceMessageEvent(TelegramBot bot, T originMessage) {
        super(bot, originMessage);
    }
}
