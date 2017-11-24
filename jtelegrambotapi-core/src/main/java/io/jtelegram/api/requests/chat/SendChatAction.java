package io.jtelegram.api.requests.chat;

import io.jtelegram.api.chat.ChatAction;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendChatAction extends UpdateTelegramRequest {
    private ChatId chatId;
    private ChatAction action;

    public SendChatAction(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, ChatAction action) {
        super("sendChatAction", errorHandler, callback);
        this.chatId = chatId;
        this.action = action;
    }

    @Override
    protected boolean isValid() {
        return chatId != null && action != null;
    }
}
