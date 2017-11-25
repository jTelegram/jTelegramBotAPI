package io.jtelegram.api.inline.input;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InputContactMessageContent extends InputMessageContent {
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
