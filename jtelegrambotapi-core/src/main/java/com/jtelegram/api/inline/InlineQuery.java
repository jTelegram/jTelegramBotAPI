package com.jtelegram.api.inline;

import com.jtelegram.api.user.User;
import com.jtelegram.api.message.media.Location;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InlineQuery {
    private String id;
    private User from;
    private Location location;
    private String query;
    private String offset;
}
