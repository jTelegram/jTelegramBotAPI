package io.jtelegram.api.commands;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.commands.listeners.CommandListener;
import io.jtelegram.api.events.message.TextMessageEvent;
import io.jtelegram.api.message.entity.MessageEntity;
import io.jtelegram.api.message.entity.MessageEntityType;
import io.jtelegram.api.message.impl.TextMessage;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CommandRegistry {
    private final TelegramBot bot;
    private final Map<CommandListener, CommandHandler> listeners = new HashMap<>();

    public void handle(TextMessageEvent event) {
        TextMessage message = event.getMessage();
        List<MessageEntity> entities = message.getEntities();

        String messageText = message.getText();

        String baseCommand = null;
        for (MessageEntity messageEntity : entities) {
            if (messageEntity.getType() == MessageEntityType.BOT_COMMAND) {
                baseCommand = messageText.substring(messageEntity.getOffset(), messageEntity.getLength());
            }
        }

        String args[] = messageText.split(" ");
        List<String> argsList = new ArrayList<>(args.length > 1 ? args.length : 0);

        if (args.length > 1) {
            argsList.addAll(Arrays.asList(args).subList(1, args.length));
        }

        Command command = new Command(baseCommand, argsList, message);

        listeners.entrySet()
                .stream()
                .filter(e -> e.getKey().trigger(command.getBaseCommand()))
                .forEach(e -> e.getValue().onCommand(command));
    }
}