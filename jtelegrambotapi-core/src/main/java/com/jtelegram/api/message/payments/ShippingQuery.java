package com.jtelegram.api.message.payments;

import com.jtelegram.api.update.UpdateContents;
import com.jtelegram.api.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ShippingQuery implements UpdateContents {
    private final String id;
    private final User from;
    private final String invoicePayload;
    private final ShippingAddress shippingAddress;
}
