package io.jtelegram.api.message;

import io.jtelegram.api.message.impl.*;
import lombok.Getter;

public enum MessageType {
    TEXT(TextMessage.class),
    AUDIO(AudioMessage.class),
    DOCUMENT(ContactMessage.class),
    GAME(DocumentMessage.class),
    PHOTO(PhotoMessage.class),
    STICKER(StickerMessage.class),
    VIDEO(VideoMessage.class),
    VOICE(VoiceMessage.class),
    VIDEO_NOTE(VideoNoteMessage.class),
    CONTACT(ContactMessage.class),
    LOCATION(LocationMessage.class),
    VENUE(VenueMessage.class)
    ;

    @Getter
    private Class<? extends Message> correspondingClass;

    MessageType(Class<? extends Message> correspondingClass) {
        this.correspondingClass = correspondingClass;
    }
}
