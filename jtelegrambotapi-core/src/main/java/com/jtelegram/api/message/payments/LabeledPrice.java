package com.jtelegram.api.message.payments;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LabeledPrice {
    private final String label;
    private final Integer amount;
}
