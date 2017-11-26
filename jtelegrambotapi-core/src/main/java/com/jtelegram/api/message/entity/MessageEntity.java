package com.jtelegram.api.message.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageEntity {
    private MessageEntityType type;
    private int offset;
    private int length;
    private String content;

    public void updateContent(String text) {
        this.content = text.substring(offset, offset + length);
    }
}
