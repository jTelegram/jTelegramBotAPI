package com.jtelegram.api.message.passport.element.datable;

import com.jtelegram.api.message.passport.element.FrontablePassportElement;
import lombok.ToString;

/**
 * This is *actually* a passport, in contrast
 * to a Telegram Passport
 */
@ToString(callSuper = true)
public class PassportPassportElement extends FrontablePassportElement {
}
