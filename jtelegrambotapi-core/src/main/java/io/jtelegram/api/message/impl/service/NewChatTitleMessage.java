package io.jtelegram.api.message.impl.service;

import lombok.Getter;

@Getter
public class NewChatTitleMessage extends ServiceMessage {
    private String newChatTitle;
}
