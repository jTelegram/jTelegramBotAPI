package com.jtelegram.api.message.passport;

import com.jtelegram.api.message.passport.element.EncryptedPassportElement;
import lombok.Getter;

import java.util.List;

@Getter
public class TelegramPassport {
    private List<EncryptedPassportElement> elements;
    private EncryptedPassportCredentials credentials;
}
