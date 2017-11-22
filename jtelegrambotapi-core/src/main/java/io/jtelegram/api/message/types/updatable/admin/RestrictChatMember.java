package io.jtelegram.api.message.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.types.updatable.helpers.UserAdminChatRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class RestrictChatMember extends UserAdminChatRequest {
    private final Long untilDate;
    private final Boolean canSendMessages;
    private final Boolean canSendMediaMessages;
    private final Boolean canSendOtherMessages;
    private final Boolean canAddWebPagePreviews;

    @Builder
    public RestrictChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId, Long untilDate, Boolean canSendMessages, Boolean canSendMediaMessages, Boolean canSendOtherMessages, Boolean canAddWebPagePreviews) {
        super("restrictChatMember", errorHandler, callback, chatId, userId);
        this.untilDate = untilDate;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }
}
