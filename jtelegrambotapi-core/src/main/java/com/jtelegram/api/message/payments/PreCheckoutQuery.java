package com.jtelegram.api.message.payments;

import com.jtelegram.api.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PreCheckoutQuery {
    private final String id;
    private final User from;
    private final String currency;
    private final Integer totalAmount;
    private final String invoicePayload;
    private final String shippingOptionId;
    private final OrderInfo orderInfo;
}
