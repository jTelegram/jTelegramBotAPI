package io.jtelegram.api.requests.sticker;

import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.input.file.IdInputFile;
import io.jtelegram.api.message.media.FileMedium;
import io.jtelegram.api.message.sticker.Sticker;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SetStickerPosition extends UpdateTelegramRequest {
    /**
     * File Id of the sticker
     *
     * @see Sticker
     * @see IdInputFile#of(FileMedium)
     */
    private IdInputFile sticker;
    /**
     * Zero-based position on the set
     */
    private int position;

    @Builder
    private SetStickerPosition(Consumer<TelegramException> errorHandler, Runnable callback, IdInputFile sticker, int position) {
        super("setStickerPositionInSet", errorHandler, callback);
        this.sticker = sticker;
        this.position = position;
    }

    @Override
    protected boolean isValid() {
        return position >= 0 && sticker != null;
    }
}
