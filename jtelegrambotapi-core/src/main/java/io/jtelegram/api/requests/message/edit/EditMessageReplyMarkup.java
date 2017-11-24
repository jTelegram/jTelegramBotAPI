package io.jtelegram.api.requests.message.edit;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.requests.message.framework.req.EditMessageRequest;
import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class EditMessageReplyMarkup extends EditMessageRequest<Message> {
    @Builder
    public EditMessageReplyMarkup(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup) {
        super("editMessageReplyMarkup", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
    }
}
