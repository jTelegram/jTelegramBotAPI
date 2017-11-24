package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.framework.UpdateTelegramRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class DeleteChatStickerSet extends UpdateTelegramRequest {
    private ChatId chatId;

    public DeleteChatStickerSet(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId) {
        super("deleteChatStickerSet", errorHandler, callback);
        this.chatId = chatId;
    }

    @Override
    protected boolean isValid() {
        return chatId != null;
    }
}
