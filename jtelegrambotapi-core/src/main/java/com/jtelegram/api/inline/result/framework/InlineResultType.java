package com.jtelegram.api.inline.result.framework;

import com.jtelegram.api.inline.result.*;
import com.jtelegram.api.inline.result.cached.*;
import io.jtelegram.api.inline.result.*;
import io.jtelegram.api.inline.result.cached.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InlineResultType {
    CACHED_AUDIO(InlineCachedResultAudio.class),
    CACHED_DOCUMENT(InlineCachedResultDocument.class),
    CACHED_GIF(InlineCachedResultGif.class),
    CACHED_MPEG4_GIF(InlineCachedResultMpegGif.class),
    CACHED_PHOTO(InlineCachedResultPhoto.class),
    CACHED_STICKER(InlineCachedResultSticker.class),
    CACHED_VIDEO(InlineCachedResultVideo.class),
    CACHED_VOICE(InlineCachedResultVoice.class),
    ARTICLE(InlineResultArticle.class),
    AUDIO(InlineResultAudio.class),
    CONTACT(InlineResultContact.class),
    GAME(InlineResultGame.class),
    DOCUMENT(InlineResultDocument.class),
    GIF(InlineResultGif.class),
    LOCATION(InlineResultLocation.class),
    MPEG4_GIF(InlineResultMpegGif.class),
    PHOTO(InlineResultPhoto.class),
    VENUE(InlineResultVenue.class),
    VIDEO(InlineResultVideo.class),
    VOICE(InlineResultVoice.class)
    ;

    private Class<? extends InlineResult> implementationClass;

    public static InlineResultType typeFrom(Class<? extends InlineResult> clazz) {
        for (InlineResultType type : values()) {
            if (type.implementationClass.equals(clazz))
                return type;
        }

        return null;
    }
}
