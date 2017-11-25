package io.jtelegram.api.message.impl.service;

import io.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NewChatMembersMessage extends ServiceMessage {
    private List<User> newChatMembers;
}
