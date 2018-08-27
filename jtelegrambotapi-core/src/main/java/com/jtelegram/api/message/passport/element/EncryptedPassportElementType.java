package com.jtelegram.api.message.passport.element;

import com.jtelegram.api.message.passport.element.datable.*;
import com.jtelegram.api.message.passport.element.files.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EncryptedPassportElementType {
    PERSONAL_DETAILS(PersonalDetailsPassportElement.class),
    PASSPORT(PassportPassportElement.class),
    DRIVER_LICENSE(DriversLicensePassportElement.class),
    IDENTITY_CARD(IdentityCardPassportElement.class),
    INTERNAL_PASSPORT(InternalPassportPassportElement.class),
    ADDRESS(AddressPassportElement.class),
    UTILITY_BILL(UtilityBillPassportElement.class),
    BANK_STATEMENT(BankStatementPassportElement.class),
    RENTAL_AGREEMENT(RentalAgreementPassportElement.class),
    PASSPORT_REGISTRATION(PassportRegistrationPassportElement.class),
    TEMPORARY_REGISTRATION(TemporaryRegistrationPassportRegistration.class),
    PHONE_NUMBER(PhoneNumberPassportElement.class),
    EMAIL(EmailPassportElement.class);

    private final Class<? extends EncryptedPassportElement> implementingClass;
}
