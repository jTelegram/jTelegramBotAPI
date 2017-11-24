package io.jtelegram.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TelegramFile {
    private String fileId;
    private int fileSize;
    private String filePath;
}
