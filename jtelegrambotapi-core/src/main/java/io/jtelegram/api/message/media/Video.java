package io.jtelegram.api.message.media;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Video extends VisualFileMedium implements DuratableMedium, MimeableMedium, ThumbableMedium {
    private long duration;
    @SerializedName("thumb")
    private PhotoSize thumbnail;
    private String mimeType;
}
