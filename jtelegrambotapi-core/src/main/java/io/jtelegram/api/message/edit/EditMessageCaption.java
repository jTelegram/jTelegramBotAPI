package io.jtelegram.api.message.edit;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class EditMessageCaption extends EditMessageRequest<Message> {
    private String caption;

    @Builder
    public EditMessageCaption(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, int messageId, String inlineMessageId, ReplyMarkup replyMarkup, String caption) {
        super("editMessage", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId, replyMarkup);
        this.caption = caption;
    }
}
