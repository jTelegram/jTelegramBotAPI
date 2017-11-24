package io.jtelegram.api.message.requests.types.sendable.extras;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.sendable.helpers.SendableChatRequest;

import java.util.function.Consumer;

public class GetChat extends SendableChatRequest<Chat> {
    protected GetChat(Consumer<Chat> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("getChat", Chat.class, callback, errorHandler, chatId);
    }
}
