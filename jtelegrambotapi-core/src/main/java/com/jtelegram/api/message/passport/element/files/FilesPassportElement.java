package com.jtelegram.api.message.passport.element.files;

import com.jtelegram.api.message.passport.TelegramPassportFile;
import com.jtelegram.api.message.passport.element.TranslateablePassportElement;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class FilesPassportElement extends TranslateablePassportElement {
    private List<TelegramPassportFile> passportFiles;
}
