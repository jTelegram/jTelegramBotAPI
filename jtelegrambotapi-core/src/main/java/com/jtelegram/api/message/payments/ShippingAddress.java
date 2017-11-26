package com.jtelegram.api.message.payments;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ShippingAddress {
    private final String country_code;
    private final String state;
    private final String city;
    private final String streetLine1;
    private final String streetLine2;
    private final String postCode;
}
