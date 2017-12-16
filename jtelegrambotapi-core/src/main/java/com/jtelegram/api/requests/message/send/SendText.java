package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import com.jtelegram.api.util.TextBuilder;
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

    public static class SendTextBuilder {
        public SendTextBuilder text(TextBuilder builder) {
            this.parseMode = ParseMode.HTML;
            this.text = builder.toHtml();
            return this;
        }
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && text != null;
    }

}