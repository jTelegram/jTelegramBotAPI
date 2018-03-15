package com.jtelegram.api.message.games;

import com.jtelegram.api.message.media.PhotoSize;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Animation {
    private final String fileId;
    private final PhotoSize thumb;
    private final String fileName;
    private final String mimeType;
    private final Integer fileSize;
}
