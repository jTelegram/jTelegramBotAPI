package com.jtelegram.api.message.games;

import com.jtelegram.api.message.media.PhotoSize;
import com.jtelegram.api.message.entity.MessageEntity;
import lombok.AccessLevel;
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
