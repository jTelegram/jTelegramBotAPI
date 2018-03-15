package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Audio extends FileMedium implements DuratableMedium, MimeableMedium {
    private long duration;
    private String performer;
    private String title;
    private String mimeType;
}
