package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.service.ServiceMessage;

public abstract class ServiceMessageEvent<T extends ServiceMessage> extends MessageEvent<T> {
    protected ServiceMessageEvent(TelegramBot bot, T originMessage) {
        super(bot, originMessage);
    }
}
