package io.jtelegram.api.requests.chat.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.requests.message.framework.req.SendableChatRequest;
import lombok.Builder;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
public class ExportChatInviteLink extends SendableChatRequest<String> {
    @Builder
    protected ExportChatInviteLink(Consumer<String> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("exportChatInviteLink", String.class, callback, errorHandler, chatId);
    }
}
