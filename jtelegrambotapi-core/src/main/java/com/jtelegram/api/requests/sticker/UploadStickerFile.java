package com.jtelegram.api.requests.sticker;

import com.google.gson.annotations.SerializedName;
import com.jtelegram.api.requests.framework.QueryTelegramRequest;
import com.jtelegram.api.TelegramFile;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.message.sticker.Sticker;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class UploadStickerFile extends QueryTelegramRequest<TelegramFile> {
    private Integer userId;
    @SerializedName("png_sticker")
    private InputFile sticker;

    @Builder
    protected UploadStickerFile(Consumer<TelegramFile> callback, Consumer<TelegramException> errorHandler, Integer userId, InputFile sticker) {
        super("uploadSticker", TelegramFile.class, callback, errorHandler);
        this.userId = userId;
        this.sticker = sticker;
    }

    @Override
    protected boolean isValid() {
        return userId != null && sticker != null && Sticker.verifySize(sticker);
    }
}
