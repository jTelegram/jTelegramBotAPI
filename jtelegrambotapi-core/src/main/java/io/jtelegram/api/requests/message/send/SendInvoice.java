package io.jtelegram.api.requests.message.send;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.payments.LabeledPrice;
import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import io.jtelegram.api.requests.message.framework.req.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Currency;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class SendInvoice extends SendableMessageRequest<Message> {
    private String title;
    private String description;
    private String payload;
    private String providerToken;
    private String startParameter;
    private Currency currency;
    private List<LabeledPrice> prices;
    private String providerData;
    private String photoUrl;
    private Integer photoSize;
    private Integer photoWidth;
    private Integer photoHeight;
    private Boolean needName;
    private Boolean needPhoneNumber;
    private Boolean needEmail;
    private Boolean needShippingAddress;
    private Boolean isFlexible;

    @Builder
    private SendInvoice(Consumer<Message> callback, Consumer<TelegramException> errorHandler, ChatId chatId,
                       Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, String title,
                       String description, String payload, String providerToken, String startParameter, Currency currency,
                       List<LabeledPrice> prices, String providerData, String photoUrl, Integer photoSize, Integer photoWidth,
                       Integer photoHeight, Boolean needName, Boolean needPhoneNumber, Boolean needEmail, Boolean needShippingAddress,
                       Boolean isFlexible) {
        super("sendInvoice", Message.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.title = title;
        this.description = description;
        this.payload = payload;
        this.providerToken = providerToken;
        this.startParameter = startParameter;
        this.currency = currency;
        this.prices = prices;
        this.providerData = providerData;
        this.photoUrl = photoUrl;
        this.photoSize = photoSize;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.needName = needName;
        this.needPhoneNumber = needPhoneNumber;
        this.needEmail = needEmail;
        this.needShippingAddress = needShippingAddress;
        this.isFlexible = isFlexible;
    }
}
