package com.jtelegram.api.requests.sticker;

import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.sticker.StickerSet;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class GetStickerSet extends QueryTelegramRequest<StickerSet> {
    private String name;

    @Builder
    protected GetStickerSet(Consumer<StickerSet> callback, Consumer<TelegramException> errorHandler, String name) {
        super("getStickerSet", StickerSet.class, callback, errorHandler);
        this.name = name;
    }

    @Override
    protected boolean isValid() {
        return name != null;
    }
}
