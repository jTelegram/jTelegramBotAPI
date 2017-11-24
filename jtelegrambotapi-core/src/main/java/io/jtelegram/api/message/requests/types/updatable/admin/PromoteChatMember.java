package io.jtelegram.api.message.requests.types.updatable.admin;

import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.requests.types.updatable.helpers.UserAdminChatRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class PromoteChatMember extends UserAdminChatRequest {
    private final Boolean canChangeInfo;
    private final Boolean canPostMessages;
    private final Boolean canEditMessages;
    private final Boolean canDeleteMessages;
    private final Boolean canInviteUsers;
    private final Boolean canRestrictMembers;
    private final Boolean canPinMessages;
    private final Boolean canPromoteMembers;

    @Builder
    public PromoteChatMember(Consumer<TelegramException> errorHandler, Runnable callback, ChatId chatId, Integer userId, Boolean canChangeInfo, Boolean canPostMessages, Boolean canEditMessages, Boolean canDeleteMessages, Boolean canInviteUsers, Boolean canRestrictMembers, Boolean canPinMessages, Boolean canPromoteMembers) {
        super("promoteChatMember", errorHandler, callback, chatId, userId);
        this.canChangeInfo = canChangeInfo;
        this.canPostMessages = canPostMessages;
        this.canEditMessages = canEditMessages;
        this.canDeleteMessages = canDeleteMessages;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessages = canPinMessages;
        this.canPromoteMembers = canPromoteMembers;
    }
}
