package com.jtelegram.api.inline.result.framework;

import com.jtelegram.api.inline.result.InlineResultArticle;
import com.jtelegram.api.inline.result.InlineResultAudio;
import com.jtelegram.api.inline.result.InlineResultContact;
import com.jtelegram.api.inline.result.InlineResultDocument;
import com.jtelegram.api.inline.result.InlineResultGame;
import com.jtelegram.api.inline.result.InlineResultGif;
import com.jtelegram.api.inline.result.InlineResultLocation;
import com.jtelegram.api.inline.result.InlineResultMpegGif;
import com.jtelegram.api.inline.result.InlineResultPhoto;
import com.jtelegram.api.inline.result.InlineResultVenue;
import com.jtelegram.api.inline.result.InlineResultVideo;
import com.jtelegram.api.inline.result.InlineResultVoice;
import com.jtelegram.api.inline.result.cached.InlineCachedResultAudio;
import com.jtelegram.api.inline.result.cached.InlineCachedResultDocument;
import com.jtelegram.api.inline.result.cached.InlineCachedResultGif;
import com.jtelegram.api.inline.result.cached.InlineCachedResultMpegGif;
import com.jtelegram.api.inline.result.cached.InlineCachedResultPhoto;
import com.jtelegram.api.inline.result.cached.InlineCachedResultSticker;
import com.jtelegram.api.inline.result.cached.InlineCachedResultVideo;
import com.jtelegram.api.inline.result.cached.InlineCachedResultVoice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
