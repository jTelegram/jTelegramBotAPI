package io.jtelegram.api.message.requests.types.sendable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.sendable.helpers.SendableChatRequest;
import lombok.Builder;

import java.util.function.Consumer;

public class ExportChatInviteLink extends SendableChatRequest<String> {
    @Builder
    protected ExportChatInviteLink(Consumer<String> callback, Consumer<TelegramException> errorHandler, ChatId chatId) {
        super("exportChatInviteLink", String.class, callback, errorHandler, chatId);
    }
}
