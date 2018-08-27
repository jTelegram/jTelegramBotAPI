package com.jtelegram.api.message.passport.element.datable;

import com.jtelegram.api.message.passport.element.TranslateablePassportElement;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PersonalDetailsPassportElement extends TranslateablePassportElement implements DatablePassportElement {
    private String data;
}
