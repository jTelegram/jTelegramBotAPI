package com.jtelegram.api.message.passport;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TelegramPassportFile {
    private String fileId;
    private long fileSize;
    private long fileDate;
}
