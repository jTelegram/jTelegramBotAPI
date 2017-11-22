package io.jtelegram.api.message.edit;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class DeleteMessage extends UpdateTelegramRequest {
    private ChatId chatId;
    private int messageId;

    public DeleteMessage(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, int messageId) {
        super("deleteMessage", errorHandler, callback);
        this.chatId = chatId;
        this.messageId = messageId;
    }
}
