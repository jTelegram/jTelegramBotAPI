package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.UpdatableChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class SetChatTitle extends UpdatableChatRequest {
    private final String title;

    @Builder
    public SetChatTitle(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, String title) {
        super("setChatTitle", errorHandler, callback, chatId);
        this.title = title;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && title != null;
    }
}
