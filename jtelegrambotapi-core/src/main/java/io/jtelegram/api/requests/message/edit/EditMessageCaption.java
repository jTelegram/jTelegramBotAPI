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
public class EditMessageCaption extends EditMessageRequest<Message> {
    private String caption;

    @Builder
    public EditMessageCaption(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup, String caption) {
        super("editMessage", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
        this.caption = caption;
    }
}
