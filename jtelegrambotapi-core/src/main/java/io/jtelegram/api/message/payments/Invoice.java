package io.jtelegram.api.message.payments;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Currency;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class Invoice {
    private final String title;
    private final String description;
    private final String startParameter;
    // We should really use java's currency api...
    private final Currency currency;
    private final Integer totalAmount;

}
