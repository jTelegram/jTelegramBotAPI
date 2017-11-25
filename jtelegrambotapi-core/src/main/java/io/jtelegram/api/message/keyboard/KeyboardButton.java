package io.jtelegram.api.message.keyboard;

import lombok.*;

@Getter
@ToString
@Builder
public class KeyboardButton {
    private String text;
    private Boolean requestContact;
    private Boolean requestLocation;
}
