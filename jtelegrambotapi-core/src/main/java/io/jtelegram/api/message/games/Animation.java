package io.jtelegram.api.message.games;

import io.jtelegram.api.message.media.PhotoSize;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class Animation {
    private final String fileId;
    private final PhotoSize thumb;
    private final String fileName;
    private final String mimeType;
    private final Integer fileSize;
}
