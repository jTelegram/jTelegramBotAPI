package io.jtelegram.api.events.chat;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.message.ServiceMessageEvent;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.impl.service.PinnedMessageMessage;
import lombok.Getter;

@Getter
public class PinnedMessageEvent extends ServiceMessageEvent<PinnedMessageMessage> {
    private Message pinnedMessage;

    public PinnedMessageEvent(TelegramBot bot, PinnedMessageMessage originMessage) {
        super(bot, originMessage);
        this.pinnedMessage = originMessage.getPinnedMessage();
    }
}
