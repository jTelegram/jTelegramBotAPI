package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class FilesPassportError extends PassportElementError {
    public static final List<EncryptedPassportElementType> VALID_TYPES = Arrays.asList(
            EncryptedPassportElementType.UTILITY_BILL,
            EncryptedPassportElementType.BANK_STATEMENT,
            EncryptedPassportElementType.RENTAL_AGREEMENT,
            EncryptedPassportElementType.PASSPORT_REGISTRATION,
            EncryptedPassportElementType.TEMPORARY_REGISTRATION
    );
    private List<String> fileHashes;

    @Builder
    public FilesPassportError(EncryptedPassportElementType type, String message, List<String> fileHashes) {
        super("files", type, message);
        this.fileHashes = fileHashes;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && fileHashes != null && !fileHashes.isEmpty() && VALID_TYPES.contains(getType());
    }
}
