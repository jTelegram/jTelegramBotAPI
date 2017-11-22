package io.jtelegram.api.message.types.sendable.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ParseMode {
    NONE(""),
    MARKDOWN("Markdown"),
    HTML("HTML");
    private final String mode;
}
