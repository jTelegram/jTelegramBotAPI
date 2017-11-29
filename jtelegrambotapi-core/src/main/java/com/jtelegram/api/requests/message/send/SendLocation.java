package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendLocation extends SendableMessageRequest<Message> {
    private Integer livePeriod;
    private Float latitude;
    private Float longitude;

    @Builder
    private SendLocation(Consumer<Message> callback, Consumer<TelegramException> errorHandler,
                        ChatId chatId, Integer replyToMessageId, Boolean disableNotification,
                        ReplyMarkup replyMarkup, Integer livePeriod, Float latitude, Float longitude) {
        super("sendLocation", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.livePeriod = livePeriod;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && latitude != null && longitude != null;
    }
}
