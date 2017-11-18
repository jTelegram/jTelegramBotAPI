package io.jtelegram.api.message.entity;

import io.jtelegram.api.message.impl.TextMessage;
import lombok.Getter;

@Getter
public class MessageEntity {
    private MessageEntityType type;
    private int offset;
    private int length;
    private String content;

    public void updateContent(TextMessage origin) {
        this.content = origin.getText().substring(offset, offset + length);
    }
}
