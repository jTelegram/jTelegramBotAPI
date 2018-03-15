package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.payments.Invoice;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InvoiceMessage extends Message<Invoice> {
    private Invoice invoice;

    @Override
    public Invoice getContent() {
        return invoice;
    }
}
