package com.jtelegram.api.commands;

import com.jtelegram.api.message.impl.TextMessage;
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
    private final String[] args;
    private final TextMessage baseMessage;

    public String getArgsAsText() {
        return String.join(" ", args);
    }
}
