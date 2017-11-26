package com.jtelegram.api.message.games;

import com.jtelegram.api.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GameHighScore {
    private final Integer position;
    private final User user;
    private final Integer score;
}
