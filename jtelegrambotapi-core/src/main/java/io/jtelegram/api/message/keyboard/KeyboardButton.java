package io.jtelegram.api.message.keyboard;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyboardButton {
    private String text;
    private Boolean requestContact;
    private Boolean requestLocation;
}
