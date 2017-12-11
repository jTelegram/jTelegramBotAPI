package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.message.input.file.ExternalInputFile;
import com.jtelegram.api.message.input.file.IdInputFile;
import com.jtelegram.api.message.input.file.LocalInputFile;
import com.jtelegram.api.requests.message.send.SendPhoto;

import java.net.MalformedURLException;
import java.net.URL;

public class PhotoTest extends ResourceTestModule {
    public PhotoTest(TelegramBot bot) {
        super(bot, "/test-photo.jpg");
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        Chat chat = command.getChat();

        bot.perform(SendPhoto.builder()
                .chatId(ChatId.of(chat))
                .photo(new LocalInputFile(getResourceFile()))
                .caption("Local Photo Test")
                .callback((message) -> {
                    bot.perform(SendPhoto.builder()
                            .chatId(ChatId.of(chat))
                            .photo(IdInputFile.of(message.getHighestResolutionPhoto()))
                            .caption("Photo File Id Test")
                            .build());

                    URL url;

                    try {
                        url = new URL("https://telegram.org/img/t_logo.png");
                    } catch (MalformedURLException no) {
                        return;
                    }

                    bot.perform(SendPhoto.builder()
                            .chatId(ChatId.of(chat))
                            .photo(new ExternalInputFile(url))
                            .caption("External Photo Test")
                            .build());
                })
                .build());
    }

    @Override
    public String getName() {
        return "photo-test";
    }
}
