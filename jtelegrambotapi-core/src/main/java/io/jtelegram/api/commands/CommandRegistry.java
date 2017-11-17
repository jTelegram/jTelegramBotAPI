package io.jtelegram.api.commands;

import io.jtelegram.api.TelegramBot;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class CommandRegistry {
    private final TelegramBot bot;
    private final Set<CommandListener> commandListerns = new HashSet<>();
}