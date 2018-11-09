package com.jtelegram.api.requests.message.edit;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.requests.message.framework.req.SendableInlineRequest;
import java.util.Objects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class EditMessageLiveLocation extends SendableInlineRequest<Message> {
    private final Float latitude;
    private final Float longitude;
    private final Integer livePeriod;

    @Builder
    protected EditMessageLiveLocation(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer messageId, String inlineMessageId, Float latitude, Float longitude, Integer livePeriod) {
        super("editMessageLiveLocation", Message.class, callback, errorHandler, chatId, messageId, inlineMessageId);
        this.latitude = latitude;
        this.longitude = longitude;
        this.livePeriod = livePeriod;
    }

    @Override
    protected boolean isValid() {
        boolean valid = super.isValid() && latitude != null && longitude != null;
        if (livePeriod != null) {
            valid = valid & livePeriod >= 60;
            valid = valid & livePeriod <= 86400;
        }
        return valid;
    }

    public static EditMessageLiveLocationBuilder forMessage(LocationMessage message) {
        Objects.requireNonNull(message, "message cannot be null");
        return builder()
                .chatId(message.getChat().getChatId())
                .messageId(message.getMessageId());
    }

    public static EditMessageLiveLocationBuilder forInlineMessage(String inlineMessageId) {
        Objects.requireNonNull(inlineMessageId, "inline message ID cannot be null");
        return builder().inlineMessageId(inlineMessageId);
    }
}
