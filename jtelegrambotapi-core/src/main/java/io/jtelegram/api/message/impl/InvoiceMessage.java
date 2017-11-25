package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.payments.Invoice;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InvoiceMessage extends Message<Invoice> {
    private Invoice invoice;

    @Override
    public Invoice getContent() {
        return invoice;
    }
}
