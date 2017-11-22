package io.jtelegram.api.message.edit;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class EditMessageRequest<T> extends QueryTelegramRequest<T> {
    private ChatId chatId;
    private int messageId;
    private String inlineMessageId;
    private ReplyMarkup replyMarkup;

    protected EditMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback,
                                 Consumer<TelegramException> errorHandler, ChatId chatId, int messageId,
                                 String inlineMessageId, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler);
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
        this.replyMarkup = replyMarkup;
    }

    @Override
    protected boolean isValid() {
        return (chatId != null && messageId != 0 || inlineMessageId != null);
    }
}
