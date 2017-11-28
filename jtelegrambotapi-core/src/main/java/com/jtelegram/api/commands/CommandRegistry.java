package com.jtelegram.api.commands;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.commands.filters.CommandFilter;
import com.jtelegram.api.commands.filters.TextFilter;
import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;
import com.jtelegram.api.message.impl.TextMessage;

import java.util.*;
import java.util.stream.Collectors;

public class CommandRegistry implements EventHandler<TextMessageEvent> {
    private final TelegramBot bot;
    private final List<CommandFilter> listeners = new ArrayList<>();

    public CommandRegistry(TelegramBot bot) {
        this.bot = bot;
        bot.getEventRegistry().registerEvent(TextMessageEvent.class, this);
    }

    public void registerCommand(CommandFilter filter) {
        this.listeners.add(filter);
    }

    public void registerCommand(String command, CommandFilter filter) {
        this.registerCommand(new TextFilter(command, false, filter));
    }

    @Override
    public void onEvent(TextMessageEvent event) {
        TextMessage message = event.getMessage();

        List<MessageEntity> entities = message.getEntities();
        Optional<String> baseCommand = entities.stream()
                .filter(me -> me.getOffset() == 0)
                .filter(me -> me.getType() == MessageEntityType.BOT_COMMAND)
                .map(me -> me.getContent().substring(1))
                .findAny();

        if (!baseCommand.isPresent()) {
            return; // not a command
        }

        String[] args = message.getText().split(" ");
        String[] argsArray = Arrays.copyOfRange(args, 1, args.length);
        List<String> argsList = Collections.unmodifiableList(
                Arrays.stream(argsArray)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );

        Command command = new Command(baseCommand.get(), argsList, message);
        long handled = listeners.stream()
                .filter(e -> e.test(event, command))
                .count();
        // Log number of handlers that used the command?
    }
}
