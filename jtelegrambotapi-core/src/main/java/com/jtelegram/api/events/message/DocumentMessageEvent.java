package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.DocumentMessage;

public class DocumentMessageEvent extends MessageEvent<DocumentMessage> {
    public DocumentMessageEvent(TelegramBot bot, DocumentMessage message) {
        super(bot, message);
    }
}
