package com.jtelegram.api.message.impl;

import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.sticker.Sticker;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class StickerMessage extends Message<Sticker> {
    private Sticker sticker;

    @Override
    public Sticker getContent() {
        return sticker;
    }
}
