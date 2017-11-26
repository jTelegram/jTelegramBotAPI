package com.jtelegram.api.requests.sticker;

import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.message.media.FileMedium;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.sticker.Sticker;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class DeleteSticker extends UpdateTelegramRequest {
    /**
     * File ID of the sticker
     *
     * @see Sticker
     * @see IdInputFile#of(FileMedium)
     */
    private IdInputFile sticker;

    @Builder
    private DeleteSticker(Consumer<TelegramException> errorHandler, Runnable callback, IdInputFile sticker) {
        super("deleteStickerFromSet", errorHandler, callback);
        this.sticker = sticker;
    }

    @Override
    protected boolean isValid() {
        return sticker != null;
    }
}
