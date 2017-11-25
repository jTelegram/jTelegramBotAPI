package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class TextMessage extends Message<String> {
    @Getter
    private String text;
    private transient boolean entitiesUpdated = false;
    private List<MessageEntity> entities = new ArrayList<>();

    @Override
    public String getContent() {
        return text;
    }

    public List<MessageEntity> getEntities() {
        if (!entitiesUpdated) {
            entities.forEach((e) -> e.updateContent(text));
            entitiesUpdated = true;
        }

        return entities;
    }
}
