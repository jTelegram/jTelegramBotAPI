package io.jtelegram.api.requests.message.send.message;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.requests.message.framework.ParseMode;
import io.jtelegram.api.requests.message.send.framework.ReplyMarkup;
import io.jtelegram.api.requests.message.send.framework.SendableMessageRequest;
import io.jtelegram.api.chat.id.ChatId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class SendText extends SendableMessageRequest<TextMessage> {
    private final String text;
    private final ParseMode parseMode;
    private final Boolean disableWebPagePreview;

    @Builder
    protected SendText(Consumer<TextMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageID, Boolean disableNotification, String text, ParseMode parseMode, Boolean disableWebPagePreview, ReplyMarkup replyMarkup) {
        super("sendMessage", TextMessage.class, callback, errorHandler, chatId, replyToMessageID, disableNotification, replyMarkup);
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }



    @Override
    protected boolean isValid() {
        return super.isValid() && text != null;
    }

}