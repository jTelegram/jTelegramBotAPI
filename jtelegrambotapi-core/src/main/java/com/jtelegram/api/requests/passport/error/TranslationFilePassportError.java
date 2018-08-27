package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class TranslationFilePassportError extends PassportElementError {
    public static final List<EncryptedPassportElementType> VALID_TYPES = Arrays.asList(
            EncryptedPassportElementType.PASSPORT,
            EncryptedPassportElementType.DRIVER_LICENSE,
            EncryptedPassportElementType.IDENTITY_CARD,
            EncryptedPassportElementType.UTILITY_BILL,
            EncryptedPassportElementType.BANK_STATEMENT,
            EncryptedPassportElementType.RENTAL_AGREEMENT,
            EncryptedPassportElementType.PASSPORT_REGISTRATION,
            EncryptedPassportElementType.TEMPORARY_REGISTRATION
    );
    private String fileHash;

    @Builder
    public TranslationFilePassportError(EncryptedPassportElementType type, String message, String fileHash) {
        super("translation_file", type, message);
        this.fileHash = fileHash;
    }
}
