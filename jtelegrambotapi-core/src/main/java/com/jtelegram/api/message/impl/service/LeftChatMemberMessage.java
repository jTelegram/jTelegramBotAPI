package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LeftChatMemberMessage extends ServiceMessage {
    private User leftChatMember;
}
