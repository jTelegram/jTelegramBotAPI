package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
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
