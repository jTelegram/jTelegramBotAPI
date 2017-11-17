package io.jtelegram.api.chat.message.entity;

import io.jtelegram.api.chat.message.TextMessage;
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
