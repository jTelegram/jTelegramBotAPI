package io.jtelegram.api.requests.sticker;

import com.google.gson.annotations.SerializedName;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.input.file.InputFile;
import io.jtelegram.api.message.input.file.LocalInputFile;
import io.jtelegram.api.message.sticker.MaskPosition;
import io.jtelegram.api.message.sticker.Sticker;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class CreateNewStickerSet extends UpdateTelegramRequest {
    private Integer userId;
    private String name;
    private String title;
    @SerializedName("png_sticker")
    private InputFile sticker;
    private String emojis;
    private Boolean containsMasks;
    private MaskPosition maskPosition;

    @Builder
    private CreateNewStickerSet(Consumer<TelegramException> errorHandler, Runnable callback, Integer userId,
                               String name, String title, InputFile sticker, String emojis, Boolean containsMasks,
                               MaskPosition maskPosition) {
        super("createNewStickerSet", errorHandler, callback);
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.sticker = sticker;
        this.emojis = emojis;
        this.containsMasks = containsMasks;
        this.maskPosition = maskPosition;
    }

    @Override
    protected boolean isValid() {
        return userId != null && name != null && title != null && sticker != null &&
                Sticker.verifySize(sticker) && emojis != null;
    }
}
