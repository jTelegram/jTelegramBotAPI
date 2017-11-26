package com.jtelegram.api.message.payments;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Builder
public class ShippingOption {
    private final String id;
    private final String title;
    private final List<LabeledPrice> prices;
}
