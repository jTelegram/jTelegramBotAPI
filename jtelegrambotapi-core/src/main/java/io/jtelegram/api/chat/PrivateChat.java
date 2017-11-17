package io.jtelegram.api.chat;

import lombok.Getter;

@Getter
public class PrivateChat extends Chat {
    private String firstName;
    private String lastName;
}
