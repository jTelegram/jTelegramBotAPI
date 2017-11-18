package io.jtelegram.api.message;

import io.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class CaptionableMessage<T> extends Message<T> {
    private String caption;
    private List<MessageEntity> captionEntities = new ArrayList<>();
}
