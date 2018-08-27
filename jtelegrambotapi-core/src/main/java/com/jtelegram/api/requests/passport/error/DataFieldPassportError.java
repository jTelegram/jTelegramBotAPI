package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class DataFieldPassportError extends PassportElementError {
    public static final List<EncryptedPassportElementType> VALID_TYPES = Arrays.asList(
            EncryptedPassportElementType.PERSONAL_DETAILS,
            EncryptedPassportElementType.PASSPORT,
            EncryptedPassportElementType.DRIVER_LICENSE,
            EncryptedPassportElementType.IDENTITY_CARD,
            EncryptedPassportElementType.INTERNAL_PASSPORT,
            EncryptedPassportElementType.ADDRESS
    );
    private String fieldName;
    private String dataHash;

    @Builder
    public DataFieldPassportError(EncryptedPassportElementType type, String message, String fieldName, String dataHash) {
        super("data", type, message);
        this.fieldName = fieldName;
        this.dataHash = dataHash;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && fieldName != null &&
                dataHash != null && VALID_TYPES.contains(getType());
    }
}
