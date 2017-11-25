package io.jtelegram.api.message.impl.service;

import io.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeftChatMemberMessage extends ServiceMessage {
    private User leftChatMember;
}
