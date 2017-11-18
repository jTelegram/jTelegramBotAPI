package io.jtelegram.api.message.impl;

import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.sticker.Sticker;
import lombok.Getter;

@Getter
public class StickerMessage extends Message<Sticker> {
    private Sticker sticker;

    @Override
    public Sticker getContent() {
        return sticker;
    }
}
