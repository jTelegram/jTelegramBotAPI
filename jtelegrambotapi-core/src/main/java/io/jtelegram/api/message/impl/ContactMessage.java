package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.media.Contact;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContactMessage extends Message<Contact> {
    private Contact contact;

    @Override
    public Contact getContent() {
        return contact;
    }
}
