package com.jtelegram.api.events.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.impl.DocumentMessage;
import com.jtelegram.api.update.Update;
import lombok.ToString;

@ToString(callSuper = true)
public class DocumentMessageEvent extends MessageEvent<DocumentMessage> {
    public DocumentMessageEvent(TelegramBot bot, Update.MessageUpdate update, DocumentMessage message) {
        super(bot, update, message);
    }
}
