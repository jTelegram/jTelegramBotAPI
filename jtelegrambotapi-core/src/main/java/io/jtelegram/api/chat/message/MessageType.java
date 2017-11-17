package io.jtelegram.api.chat.message;

import lombok.Getter;

public enum MessageType {
    TEXT(TextMessage.class),
    AUDIO(null),
    DOCUMENT(null),
    GAME(null),
    PHOTO(null),
    STICKER(null),
    VIDEO(null),
    VOICE(null),
    VIDEO_NOTE(null),
    CONTACT(null),
    LOCATION(null),
    VENUE(null)
    ;

    @Getter
    private Class<? extends Message> correspondingClass;

    MessageType(Class<? extends Message> correspondingClass) {
        this.correspondingClass = correspondingClass;
    }
}
