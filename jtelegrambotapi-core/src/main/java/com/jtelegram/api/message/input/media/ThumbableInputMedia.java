package com.jtelegram.api.message.input.media;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public abstract class ThumbableInputMedia extends InputMedia {
    @SerializedName("thumb")
    private LocalInputFile thumbnail;

    protected ThumbableInputMedia(InputMediaType type, InputFile media, String caption,
                                  ParseMode parseMode, LocalInputFile thumbnail) {
        super(type, media, caption, parseMode);
        this.thumbnail = thumbnail;
    }

    @Override
    public List<InputFile> getAllMedia() {
        List<InputFile> media = new ArrayList<>();
        media.add(getMedia());
        media.add(thumbnail);
        media.removeIf(Objects::isNull);
        return Collections.unmodifiableList(media);
    }
}
