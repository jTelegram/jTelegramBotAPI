package com.jtelegram.api.message.passport.element;

import com.jtelegram.api.message.passport.TelegramPassportFile;
import lombok.Getter;
import lombok.ToString;

/**
 * This is indicative that the element has both a front and backside
 */
@Getter
@ToString(callSuper = true)
public class ReversablePassportElement extends FrontablePassportElement {
    private TelegramPassportFile reverseSide;
}
