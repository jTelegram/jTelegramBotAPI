package com.jtelegram.api.inline.result;

import com.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import com.jtelegram.api.inline.result.framework.DimensionalThumbableInlineResult;
import com.jtelegram.api.inline.result.framework.InlineResult;
import com.jtelegram.api.inline.input.InputMessageContent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class InlineResultVenue extends DimensionalThumbableInlineResult implements InlineResult.Titled {
    private String title;
    private String address;
    private String foursquareId;
    private Float latitude;
    private Float longitude;

    @Builder
    private InlineResultVenue(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent,
                              String thumbUrl, Integer thumbWidth, Integer thumbHeight, String title, String address,
                              String foursquareId, Float latitude, Float longitude) {
        super(id, replyMarkup, inputMessageContent, thumbUrl, thumbWidth, thumbHeight);
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
