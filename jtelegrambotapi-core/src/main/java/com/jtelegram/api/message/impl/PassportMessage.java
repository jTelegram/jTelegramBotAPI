package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.passport.TelegramPassport;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PassportMessage extends Message<TelegramPassport> {
    private TelegramPassport passportData;

    @Override
    public TelegramPassport getContent() {
        return passportData;
    }
}
