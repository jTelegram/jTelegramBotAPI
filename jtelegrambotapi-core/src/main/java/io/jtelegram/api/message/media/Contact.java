package io.jtelegram.api.message.media;

import lombok.Getter;

@Getter
public class Contact extends SendableMedium {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private long userId;
}
