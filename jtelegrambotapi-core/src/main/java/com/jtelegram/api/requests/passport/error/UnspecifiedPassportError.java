package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class UnspecifiedPassportError extends PassportElementError {
    private String elementHash;

    @Builder
    public UnspecifiedPassportError(EncryptedPassportElementType type, String message, String elementHash) {
        super("unspecified", type, message);
        this.elementHash = elementHash;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && elementHash != null;
    }
}
