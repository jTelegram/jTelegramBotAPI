package io.jtelegram.api.inline.result;

import io.jtelegram.api.inline.input.InputMessageContent;
import io.jtelegram.api.inline.keyboard.InlineKeyboardMarkup;
import io.jtelegram.api.inline.result.framework.DimensionalThumbableInlineResult;
import io.jtelegram.api.inline.result.framework.InlineResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InlineResultLocation extends DimensionalThumbableInlineResult implements InlineResult.Titled {
    private String title;
    private Integer livePeriod;
    private Float latitude;
    private Float longtitude;

    @Builder
    private InlineResultLocation(String id, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent, String thumbUrl, Integer thumbWidth,
                                 Integer thumbHeight, String title, Integer livePeriod, Float latitude, Float longtitude) {
        super(id, replyMarkup, inputMessageContent, thumbUrl, thumbWidth, thumbHeight);
        this.title = title;
        this.livePeriod = livePeriod;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
