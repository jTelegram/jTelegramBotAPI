package io.jtelegram.api.commands;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Command {
    private final String baseCommand;
    private final List<String> args;
}
