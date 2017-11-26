package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Voice extends FileMedium implements MimeableMedium, DuratableMedium {
    private long duration;
    private String mimeType;
}
