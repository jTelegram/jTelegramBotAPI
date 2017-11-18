package io.jtelegram.api.message.media;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class VideoNote extends FileMedium implements DuratableMedium, ThumbableMedium {
    private long duration;
    @SerializedName("thumb")
    private PhotoSize thumbnail;
}
