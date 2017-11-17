package io.jtelegram.api.chat.message;

import io.jtelegram.api.chat.message.entity.MessageEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class TextMessage extends Message<String> {
    private String text;
    private List<MessageEntity> entities;

    @Override
    public String getContent() {
        return text;
    }
}
