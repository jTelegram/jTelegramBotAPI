package com.jtelegram.api.requests.chat.admin;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.requests.message.framework.req.UserAdminChatRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@ToString
public class RestrictChatMember extends UserAdminChatRequest {
    private final Long untilDate;
    private final Boolean canSendMessages;
    private final Boolean canSendMediaMessages;
    private final Boolean canSendOtherMessages;
    private final Boolean canAddWebPagePreviews;

    @Builder
    public RestrictChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Long userId, Long untilDate, Boolean canSendMessages, Boolean canSendMediaMessages, Boolean canSendOtherMessages, Boolean canAddWebPagePreviews) {
        super("restrictChatMember", errorHandler, callback, chatId, userId);
        this.untilDate = untilDate;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }
}
