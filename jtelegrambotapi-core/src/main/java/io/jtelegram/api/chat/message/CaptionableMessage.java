package io.jtelegram.api.chat.message;

import lombok.Getter;

@Getter
public abstract class CaptionableMessage<T> extends Message<T> {
    private String caption;
}
