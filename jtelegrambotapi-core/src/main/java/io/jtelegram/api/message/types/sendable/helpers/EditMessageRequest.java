package io.jtelegram.api.message.types.sendable.helpers;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.sendable.helpers.ReplyMarkup;
import io.jtelegram.api.message.types.sendable.helpers.SendableChatRequest;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class EditMessageRequest<T> extends SendableChatRequest<T> {
    private int messageId;
    private String inlineMessageId;
    private ReplyMarkup replyMarkup;

    protected EditMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback,
                                 Consumer<TelegramException> errorHandler, ChatId chatId, int messageId,
                                 String inlineMessageId, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId);
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
        this.replyMarkup = replyMarkup;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && (messageId != 0 || inlineMessageId != null);
    }
}
