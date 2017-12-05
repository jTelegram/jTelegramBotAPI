package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.requests.message.send.SendLocation;
import com.jtelegram.api.test.TestModule;
import lombok.AllArgsConstructor;

public class LocationMessageTest extends AbstractTestModule {
    public LocationMessageTest(TelegramBot bot) {
        super(bot);
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        bot.perform(SendLocation.builder()
                .chatId(ChatId.of(command.getBaseMessage().getChat()))
                .latitude(0.0f)
                .longitude(0.0f)
                .errorHandler((err) -> System.out.println("Location test failed due to " + err.getDescription()))
                .build());
    }

    @Override
    public String getName() {
        return "location";
    }
}
