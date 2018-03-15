package com.jtelegram.api.message.media;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Contact extends SendableMedium {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private long userId;
}
