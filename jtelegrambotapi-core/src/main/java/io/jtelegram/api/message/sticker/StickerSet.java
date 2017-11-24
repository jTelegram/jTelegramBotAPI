package io.jtelegram.api.message.sticker;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StickerSet {
    private String name;
    private String title;
    private boolean containsMasks;
    private List<Sticker> stickers;
}
