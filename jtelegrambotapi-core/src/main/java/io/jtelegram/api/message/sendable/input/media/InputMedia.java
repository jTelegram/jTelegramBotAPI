package io.jtelegram.api.message.sendable.input.media;

import io.jtelegram.api.message.sendable.input.file.InputFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class InputMedia {
    private final InputMediaType type;
    private InputFile media;
    private String caption;
}
