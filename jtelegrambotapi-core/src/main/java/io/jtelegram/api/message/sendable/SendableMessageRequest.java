package io.jtelegram.api.message.sendable;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.sendable.chatid.ChatID;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;

import java.util.function.Consumer;

public abstract class SendableMessageRequest<T> extends QueryTelegramRequest<T> {
    private final ChatID chatID;
    private final int replyToMessageID;
    private final boolean disableNotification;

    protected SendableMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatID chatID, int replyToMessageID, boolean disableNotification) {
        super(endPoint, callbackType, callback, errorHandler);
        this.chatID = chatID;
        this.replyToMessageID = replyToMessageID;
        this.disableNotification = disableNotification;
    }


//    protected SendableMessageRequest(ChatID chatID, int replyToMessageID, boolean disableNotification) {
//        this.chatID = chatID;
//        this.replyToMessageID = replyToMessageID;
//        this.disableNotification = disableNotification;
//    }

    public abstract SendableMessageType getType();
}
