package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.requests.message.send.SendVenue;

public class VenueTest extends AbstractTestModule {
    public VenueTest(TelegramBot bot) {
        super(bot);
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        bot.perform(SendVenue.builder()
                .chatId(ChatId.of(command.getChat()))
                .latitude(49.3017049f)
                .longitude(-123.14170030000002f)
                .title("Vancouver -- Stanley Park")
                .address("Stanley Park Dr\n" +
                        "Vancouver BC V6G 3E2\n" +
                        "Canada")
                .foursquareId("4bf9d57e8f32ef3b12c504aa")
                .errorHandler((ex) -> System.out.println("Venue failed due to " + ex.getDescription()))
                .build());
    }

    @Override
    public String getName() {
        return "venue";
    }
}
