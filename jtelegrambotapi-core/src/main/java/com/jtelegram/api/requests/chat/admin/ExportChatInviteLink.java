package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.requests.message.framework.req.SendableChatRequest;
import com.jtelegram.api.ex.TelegramException;
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
