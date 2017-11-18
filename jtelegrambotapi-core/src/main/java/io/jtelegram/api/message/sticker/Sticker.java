package io.jtelegram.api.message.sticker;

import com.google.gson.annotations.SerializedName;
import io.jtelegram.api.message.media.PhotoSize;
import io.jtelegram.api.message.media.VisualFileMedium;
import lombok.Getter;

@Getter
public class Sticker extends VisualFileMedium {
    private PhotoSize thumb;
    private String emoji;
    @SerializedName("set_name")
    private String owningSet;
    private MaskPosition maskPosition;
}
