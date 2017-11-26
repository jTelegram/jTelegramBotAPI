package com.jtelegram.api.requests.sticker;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.sticker.MaskPosition;
import com.jtelegram.api.message.sticker.Sticker;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class AddSticker extends InputFileMessageUpdate {
    private Integer userId;
    private String name;
    @SerializedName("png_sticker")
    private InputFile sticker;
    private String emojis;
    private MaskPosition maskPosition;

    @Builder
    protected AddSticker(Consumer<TelegramException> errorHandler, Runnable callback, Integer userId,
                         String name, InputFile sticker, String emojis, MaskPosition maskPosition) {
        super("addStickerToSet", errorHandler, callback);
        this.userId = userId;
        this.name = name;
        this.sticker = sticker;
        this.emojis = emojis;
        this.maskPosition = maskPosition;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(sticker);
    }

    @Override
    protected boolean isValid() {
        return userId != null && name != null && sticker != null && Sticker.verifySize(sticker) && emojis != null;
    }
}
