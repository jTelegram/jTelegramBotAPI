package com.jtelegram.api;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.commands.CommandHandler;
import com.jtelegram.api.commands.listeners.RegexCommandListener;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.send.SendText;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Test {
    public static void main(String... args) {
        TelegramBot telegramBot = null;
        telegramBot.getCommandRegistry().registerCommand(new RegexCommandListener(Pattern.compile(".*")), command -> {
            TextMessage message = command.getBaseMessage();

            telegramBot.perform(SendText.builder()
                    .chatId(ChatId.of(message.getChat()))
                    .text(command.getArgsAsText())
                    .callback(textMessage -> System.out.println("It worked!"))
                    .errorHandler(e -> System.out.println("It didn't work!"))
                    .build()
            );
        });
    }
}
