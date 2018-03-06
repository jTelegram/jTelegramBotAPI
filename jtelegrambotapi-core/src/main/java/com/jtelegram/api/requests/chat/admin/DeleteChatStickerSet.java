package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class DeleteChatStickerSet extends UpdateTelegramRequest {
    private ChatId chatId;

    @Builder
    public DeleteChatStickerSet(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("deleteChatStickerSet", errorHandler, callback);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
