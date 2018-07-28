package com.jtelegram.api.message.input.media;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.framework.ParseMode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

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
        return Arrays.asList(getMedia(), thumbnail);
    }
}
