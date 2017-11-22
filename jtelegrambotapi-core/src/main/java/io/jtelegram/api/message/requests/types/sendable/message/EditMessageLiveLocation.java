package io.jtelegram.api.message.requests.types.sendable.message;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.requests.types.sendable.helpers.SendableInlineRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class EditMessageLiveLocation extends SendableInlineRequest<Message> {
    private final Long latitude;
    private final Long longitude;
    private final Integer livePeriod;

    @Builder
    protected EditMessageLiveLocation(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer messageId, String inlineMessageId, Long latitude, Long longitude, Integer livePeriod) {
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
}
