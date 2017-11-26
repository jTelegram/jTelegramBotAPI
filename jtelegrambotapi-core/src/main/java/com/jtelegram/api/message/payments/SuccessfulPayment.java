package com.jtelegram.api.message.payments;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SuccessfulPayment {
    private final String currency;
    private final Integer totalAmount;
    private final String invoicePayload;
    private final String shippingOptionId;
    private final OrderInfo orderInfo;
    private final String telegramPaymentChargeId;
    private final String providerPaymentChargeId;
}
