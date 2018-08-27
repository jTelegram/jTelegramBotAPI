package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class PassportElementError {
    private String source;
    private EncryptedPassportElementType type;
    private String message;

    protected PassportElementError(String source, EncryptedPassportElementType type, String message) {
        this.source = source;
        this.type = type;
        this.message = message;
    }

    public boolean isValid() {
        return source != null && type != null && message != null;
    }
}
