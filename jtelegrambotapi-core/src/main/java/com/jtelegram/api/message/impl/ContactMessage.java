package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.media.Contact;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ContactMessage extends Message<Contact> {
    private Contact contact;

    @Override
    public Contact getContent() {
        return contact;
    }
}
