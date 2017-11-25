package io.jtelegram.api.inline;

import io.jtelegram.api.message.media.Location;
import io.jtelegram.api.user.User;
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
