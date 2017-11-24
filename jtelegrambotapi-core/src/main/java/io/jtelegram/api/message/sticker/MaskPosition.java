package io.jtelegram.api.message.sticker;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MaskPosition {
    private MaskPoint point;
    private float xShift;
    private float yShift;
    private float scale;
}
