package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.media.PhotoSize;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class PhotoMessage extends CaptionableMessage<List<PhotoSize>> {
    private List<PhotoSize> photo = new ArrayList<>();

    @Override
    public List<PhotoSize> getContent() {
        return photo;
    }
}
