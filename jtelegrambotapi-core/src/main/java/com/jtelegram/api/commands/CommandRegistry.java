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
        MessageEntity first = !entities.isEmpty() ? entities.get(0) : null;
        String baseCommand = first != null && first.getType() == MessageEntityType.BOT_COMMAND ? first.getContent().substring(1) : null;

        if (baseCommand == null || baseCommand.isEmpty()) {
            return; // not a command
        }

        String[] args = message.getText().split(" ");
        String[] argsList = Arrays.copyOfRange(args, 1, args.length);
        Command command = new Command(baseCommand, argsList, message);
        long handled = listeners.stream()
                .filter(e -> e.test(event, command))
                .count();
        // Log number of handlers that used the command?
    }
}
