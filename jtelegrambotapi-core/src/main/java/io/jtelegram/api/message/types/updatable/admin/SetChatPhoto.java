package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UpdatableChatRequest;

import java.util.function.Consumer;

public class SetChatPhoto extends UpdatableChatRequest {
    //TODO: NOT COMPLETE, WILL HAVE TO USE FORMS OR SOMETHING.
    public SetChatPhoto(String endPoint, Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super(endPoint, errorHandler, callback, chatId);
    }
}
