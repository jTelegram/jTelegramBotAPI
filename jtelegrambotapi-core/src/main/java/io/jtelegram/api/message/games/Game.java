package io.jtelegram.api.message.games;

import io.jtelegram.api.message.entity.MessageEntity;
import io.jtelegram.api.message.media.PhotoSize;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {
    private final String title;
    private final String description;
    private final List<PhotoSize> photo;
    private final String text;
    private final List<MessageEntity> textEntities;
    private final Animation animation;
}
