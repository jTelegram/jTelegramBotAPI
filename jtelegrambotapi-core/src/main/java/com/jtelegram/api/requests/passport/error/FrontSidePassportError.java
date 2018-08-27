package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class FrontSidePassportError extends PassportElementError {
    public static final List<EncryptedPassportElementType> VALID_TYPES = Arrays.asList(
            EncryptedPassportElementType.PASSPORT,
            EncryptedPassportElementType.DRIVER_LICENSE,
            EncryptedPassportElementType.IDENTITY_CARD,
            EncryptedPassportElementType.INTERNAL_PASSPORT
    );
    private String fileHash;

    @Builder
    public FrontSidePassportError(EncryptedPassportElementType type, String message, String fileHash) {
        super("front_side", type, message);
        this.fileHash = fileHash;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && fileHash != null && VALID_TYPES.contains(getType());
    }
}
