package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.requests.message.edit.EditMessageLiveLocation;
import com.jtelegram.api.requests.message.send.SendLocation;
import com.jtelegram.api.test.TestModule;
import lombok.RequiredArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class LiveLocationTest implements TestModule {
    private final TelegramBot bot;
    private final Timer timer = new Timer();

    @Override
    public void handle(String[] args, Command command) throws Exception {
        Chat chat = command.getBaseMessage().getChat();

        bot.perform(SendLocation.builder()
                .chatId(ChatId.of(chat))
                .latitude(0.0f)
                .longitude(0.0f)
                .livePeriod(60)
                .errorHandler((err) -> System.out.println("Live Location test failed due to " + err.getDescription()))
                .callback(message -> timer.schedule(new LiveLocationTimer(chat, message), TimeUnit.SECONDS.toMillis(60)))
                .build());
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public String getName() {
        return "live-location";
    }

    @RequiredArgsConstructor
    public class LiveLocationTimer extends TimerTask {
        private float latitude = 0.0f;
        private float longitude = 0.0f;
        private final Chat chat;
        private final Message message;

        @Override
        public void run() {
            latitude++;
            longitude++;

            bot.perform(EditMessageLiveLocation.builder()
                    .chatId(ChatId.of(chat))
                    .messageId(message.getMessageId())
                    .latitude(latitude)
                    .longitude(longitude)
                    .build());
        }
    }
}
