package com.jtelegram.api.commands;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.commands.filters.CommandFilter;
import com.jtelegram.api.commands.filters.RootFilter;
import com.jtelegram.api.commands.filters.TextFilter;
import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;
import com.jtelegram.api.message.impl.TextMessage;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class CommandRegistry implements EventHandler<TextMessageEvent> {
    @Getter
    private final TelegramBot bot;
    private final List<CommandFilter> listeners = new ArrayList<>();

    public CommandRegistry(TelegramBot bot) {
        this.bot = bot;
        bot.getEventRegistry().registerEvent(TextMessageEvent.class, this);
    }

    public void registerCommand(CommandFilter filter, CommandFilter... filters) {
        this.listeners.add(new RootFilter(filter, filters));
    }

    public void registerCommand(String command, CommandFilter filter, CommandFilter... filters) {
        this.registerCommand(new TextFilter(command, false, new RootFilter(filter, filters)));
    }

    @Override
    public void onEvent(TextMessageEvent event) {
        TextMessage message = event.getMessage();

        List<MessageEntity> entities = message.getEntities();
        Optional<String> commandEntity = entities.stream()
                .filter(me -> me.getOffset() == 0)
                .filter(me -> me.getType() == MessageEntityType.BOT_COMMAND)
                .map(me -> me.getContent().substring(1))
                .findAny();

        if (!commandEntity.isPresent()) {
            return; // not a command
        }

        String baseCommand = commandEntity.get();
        String botUsername = event.getBot().getBotInfo().getUsername().toLowerCase(Locale.ROOT);
        boolean mentioned = baseCommand.toLowerCase(Locale.ROOT).endsWith("@" + botUsername);
        if (mentioned) {
            baseCommand = baseCommand.substring(0, baseCommand.indexOf('@'));
        }

        String[] args = message.getText().split(" ");
        String[] argsArray = Arrays.copyOfRange(args, 1, args.length);
        List<String> argsList = Collections.unmodifiableList(
                Arrays.stream(argsArray)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );

        Command command = new Command(baseCommand, mentioned, argsList, message);
        listeners.forEach(e -> e.test(event, command));
        // Log number of handlers that used the command?
    }
}
