package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.VenueMessage;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SendVenue extends SendableMessageRequest<VenueMessage> {
    private Float latitude;
    private Float longitude;
    private String title;
    private String address;
    private String foursquareId;
    private String foursquareType;

    @Builder
    public SendVenue(Consumer<VenueMessage> callback, Consumer<TelegramException> errorHandler,
                     ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup,
                     Float latitude, Float longitude, String title, String address, String foursquareId, String foursquareType) {
        super("sendVenue", VenueMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
        this.foursquareType = foursquareType;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && latitude != null && longitude != null && title != null && address != null;
    }
}
