package com.jtelegram.api.message.sticker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MaskPosition {
    private MaskPoint point;
    private float xShift;
    private float yShift;
    private float scale;
}
