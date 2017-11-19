package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class TextMessage extends Message<String> {
    private String text;
    private List<MessageEntity> entities = new ArrayList<>();

    @Override
    public String getContent() {
        return text;
    }
}
