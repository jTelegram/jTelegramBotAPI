package io.jtelegram.api.events.message;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.message.impl.DocumentMessage;

public class DocumentMessageEvent extends MessageEvent<DocumentMessage> {
    public DocumentMessageEvent(TelegramBot bot, DocumentMessage message) {
        super(bot, message);
    }
}
