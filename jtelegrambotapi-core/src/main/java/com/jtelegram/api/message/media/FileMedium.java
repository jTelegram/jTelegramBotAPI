package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

/**
 * This class serves as a common extension of
 * all mediums sent by file (audio, image, etc.).
 *
 */
@Getter
@ToString(callSuper = true)
public abstract class FileMedium extends SendableMedium {
    private String fileId;
    private long fileSize;
}
