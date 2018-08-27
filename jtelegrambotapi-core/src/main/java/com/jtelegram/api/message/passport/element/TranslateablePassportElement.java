package com.jtelegram.api.message.passport.element;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.message.passport.TelegramPassportFile;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class TranslateablePassportElement extends EncryptedPassportElement {
    @SerializedName("translation") // it really should be plural
    private List<TelegramPassportFile> translations;
}
