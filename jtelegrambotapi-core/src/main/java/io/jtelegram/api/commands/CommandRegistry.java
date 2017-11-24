package io.jtelegram.api.commands;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.commands.listeners.CommandListener;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CommandRegistry {
    private final TelegramBot bot;
    private final Map<CommandListener, CommandHandler> listeners = new HashMap<>();

    public void handle(String s) {
        if (!s.startsWith("/")) return;

        String baseCommand = s.substring(1, s.indexOf(' '));
        String args[] = s.split(" ");
        List<String> argsList = new ArrayList<>(args.length > 1 ? args.length : 0);

        if (args.length > 1) {
            argsList.addAll(Arrays.asList(args).subList(1, args.length));
        }

        Command command = new Command(baseCommand, argsList);

        listeners.entrySet()
                .stream()
                .filter(e -> e.getKey().trigger(baseCommand))
                .forEach(e -> e.getValue().onCommand(command));
    }
}