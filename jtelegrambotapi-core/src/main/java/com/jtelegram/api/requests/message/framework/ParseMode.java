package com.jtelegram.api.requests.message.framework;

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
