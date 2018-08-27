package com.jtelegram.api.message.passport.element;


import com.jtelegram.api.message.passport.TelegramPassportFile;
import com.jtelegram.api.message.passport.element.datable.DatablePassportElement;
import lombok.Getter;
import lombok.ToString;

/**
 * This is indicative that this element
 * contains a front side
 */
@Getter
@ToString(callSuper = true)
public class FrontablePassportElement extends TranslateablePassportElement implements DatablePassportElement {
    private String data;
    private TelegramPassportFile frontSide;
    private TelegramPassportFile selfie;
}
