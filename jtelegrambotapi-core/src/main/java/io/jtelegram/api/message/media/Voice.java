package io.jtelegram.api.message.media;

import lombok.Getter;

@Getter
public class Voice extends FileMedium implements MimeableMedium, DuratableMedium {
    private long duration;
    private String mimeType;
}
