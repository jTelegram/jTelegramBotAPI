package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.requests.message.ForwardMessage;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.test.TestModule;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ForwardMessageTest implements TestModule {
    private TelegramBot bot;

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        Chat chat = command.getChat();

        bot.perform(
                SendText.builder()
                        .text("hello this is going to be forwarded by the bot")
                        .chatId(ChatId.of(chat))
                        .callback((message) -> {
                            bot.perform(ForwardMessage.builder()
                                    .messageId(message.getMessageId())
                                    .fromChatId(ChatId.of(chat))
                                    .chatId(ChatId.of(chat))
                                    .build());
                        })
                        .build()
        );
    }

    @Override
    public String getName() {
        return "forward-message";
    }
}
