package com.jtelegram.api.requests.message.send;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.StickerMessage;
import com.jtelegram.api.message.input.file.InputFile;
import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import com.jtelegram.api.requests.message.framework.req.InputFileMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@ToString
@Getter
public class SendSticker extends InputFileMessageRequest<StickerMessage> {
    private final InputFile sticker;

    @Builder
    private SendSticker(Consumer<StickerMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId,
                        Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile sticker) {
        super("sendSticker", StickerMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.sticker = sticker;
    }

    @Override
    public List<InputFile> getInputFiles() {
        return Collections.singletonList(sticker);
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && sticker != null;
    }
}
