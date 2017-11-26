package com.jtelegram.api.inline.input;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InputVenueMessageContent extends InputMessageContent {
    private Float latitude;
    private Float longtitude;
    private String title;
    private String address;
    private String foursquareId;
}
