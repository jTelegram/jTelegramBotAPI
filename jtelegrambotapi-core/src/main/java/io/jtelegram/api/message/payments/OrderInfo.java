package io.jtelegram.api.message.payments;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderInfo {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final ShippingAddress shippingAddress;
}
