package com.jtelegram.api.message;

import com.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class CaptionableMessage<T> extends Message<T> {
    @Getter
    private String caption;
    private transient boolean entitiesUpdated = false;
    private List<MessageEntity> captionEntities = new ArrayList<>();

    public List<MessageEntity> getCaptionEntities() {
        if (!entitiesUpdated) {
            captionEntities.forEach((e) -> e.updateContent(caption));
            entitiesUpdated = true;
        }

        return captionEntities;
    }
}
