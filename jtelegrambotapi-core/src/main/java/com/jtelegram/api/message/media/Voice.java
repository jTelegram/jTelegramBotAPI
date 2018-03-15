package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Voice extends FileMedium implements MimeableMedium, DuratableMedium {
    private long duration;
    private String mimeType;
}
