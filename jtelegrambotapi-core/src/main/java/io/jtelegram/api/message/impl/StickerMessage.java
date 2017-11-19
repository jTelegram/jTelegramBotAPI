package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.sticker.Sticker;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StickerMessage extends Message<Sticker> {
    private Sticker sticker;

    @Override
    public Sticker getContent() {
        return sticker;
    }
}
