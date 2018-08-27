package com.jtelegram.api.message.passport.element.datable;

import com.jtelegram.api.message.passport.element.EncryptedPassportElement;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class AddressPassportElement extends EncryptedPassportElement implements DatablePassportElement {
    private String data;
}
