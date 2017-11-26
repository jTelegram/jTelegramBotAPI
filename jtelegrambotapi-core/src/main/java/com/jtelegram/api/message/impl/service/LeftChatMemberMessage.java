package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeftChatMemberMessage extends ServiceMessage {
    private User leftChatMember;
}
