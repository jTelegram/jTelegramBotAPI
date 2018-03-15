package com.jtelegram.api.message.sticker;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.message.media.PhotoSize;
import com.jtelegram.api.message.media.VisualFileMedium;
import com.jtelegram.api.message.input.file.InputFile;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Sticker extends VisualFileMedium {
    private PhotoSize thumb;
    private String emoji;
    @SerializedName("set_name")
    private String owningSet;
    private MaskPosition maskPosition;

    // verifies that if the input file
    // is local, that it is below 512 kb
    public static boolean verifySize(InputFile file) {
        return (!(file instanceof LocalInputFile) || ((LocalInputFile) file).getData().getTotalSpace() <= 512_000L);
    }
}
