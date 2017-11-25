package io.jtelegram.api.inline.result;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.DimensionalThumbableInlineResult;
import io.jtelegram.api.inline.result.framework.InlineResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
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
