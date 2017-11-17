package io.jtelegram.api.chat;

import lombok.Getter;

@Getter
public class Chat {
    private long id;
    private ChatType type;
    private String username;
    // todo ChatPhoto
}
