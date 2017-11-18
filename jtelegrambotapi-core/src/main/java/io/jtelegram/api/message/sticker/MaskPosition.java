package io.jtelegram.api.message.sticker;

import lombok.Getter;

@Getter
public class MaskPosition {
    private MaskPoint point;
    private float xShift;
    private float yShift;
    private float scale;
}
