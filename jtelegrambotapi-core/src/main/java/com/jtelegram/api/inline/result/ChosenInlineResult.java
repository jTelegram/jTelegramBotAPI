package com.jtelegram.api.inline.result;

import com.jtelegram.api.message.media.Location;
import com.jtelegram.api.update.UpdateContents;
import com.jtelegram.api.user.User;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "resultId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChosenInlineResult implements UpdateContents {
    private String resultId;
    private User from;
    private String query;
    private Location location;
    private String inlineMessageId;
}
