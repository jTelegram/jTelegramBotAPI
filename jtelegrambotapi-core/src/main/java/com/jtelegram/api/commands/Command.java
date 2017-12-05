package com.jtelegram.api.commands;

import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Command {
    private final String baseCommand;
    private final boolean mentioned;
    private final List<String> args;
    private final TextMessage baseMessage;

    public String getArgsAsText() {
        return String.join(" ", args);
    }

    public Chat getChat() {
        return baseMessage.getChat();
    }

    public User getSender() {
        return baseMessage.getSender();
    }
}
