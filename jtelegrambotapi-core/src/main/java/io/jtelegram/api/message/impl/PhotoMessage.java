package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.PhotoSize;
import lombok.Getter;

import java.util.List;

@Getter
public class PhotoMessage extends CaptionableMessage<List<PhotoSize>> {
    private List<PhotoSize> photo;

    @Override
    public List<PhotoSize> getContent() {
        return photo;
    }
}
