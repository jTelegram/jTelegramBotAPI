package com.jtelegram.api.message.passport;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EncryptedPassportCredentials {
    private String data;
    private String hash;
    private String secret;
}
