package com.jtelegram.api.message.impl.service;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class NewChatMembersMessage extends ServiceMessage {
    private List<User> newChatMembers;
}
