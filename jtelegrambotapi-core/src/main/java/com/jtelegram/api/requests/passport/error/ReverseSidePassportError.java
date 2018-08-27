package com.jtelegram.api.requests.passport.error;

import com.jtelegram.api.message.passport.element.EncryptedPassportElementType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class ReverseSidePassportError extends PassportElementError {
    public static final List<EncryptedPassportElementType> VALID_TYPES = Arrays.asList(
            EncryptedPassportElementType.DRIVER_LICENSE,
            EncryptedPassportElementType.IDENTITY_CARD
    );
    private String fileHash;

    @Builder
    public ReverseSidePassportError(EncryptedPassportElementType type, String message, String fileHash) {
        super("reverse_side", type, message);
        this.fileHash = fileHash;
    }


}
