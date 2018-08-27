package com.jtelegram.api.message.passport.element;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PhoneNumberPassportElement extends EncryptedPassportElement {
    private String phoneNumber;
}
