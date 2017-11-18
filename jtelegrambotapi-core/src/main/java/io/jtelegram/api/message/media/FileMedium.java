package io.jtelegram.api.message.media;

import lombok.Getter;

/**
 * This class serves as a common extension of
 * all mediums sent by file (audio, image, etc.).
 *
 */
@Getter
public abstract class FileMedium extends SendableMedium {
    private String fileId;
    private long fileSize;
}
