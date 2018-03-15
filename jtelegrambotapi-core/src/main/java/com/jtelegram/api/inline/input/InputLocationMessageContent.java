package com.jtelegram.api.inline.input;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InputLocationMessageContent extends InputMessageContent {
    private Float latitude;
    private Float longitude;
    private Integer livePeriod;
}
