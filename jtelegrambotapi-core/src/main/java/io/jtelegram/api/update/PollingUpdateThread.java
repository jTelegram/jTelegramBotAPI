package io.jtelegram.api.update;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.requests.GetUpdates;
import io.jtelegram.api.requests.framework.TelegramRequest;
import io.jtelegram.api.update.types.MessageUpdate;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PollingUpdateThread extends Thread {
    private static final Map<Class<? extends Update>, Function<Update, Event>> EVENT_FUNCTIONS = new HashMap<>();
    private int offset = 0;
    private final TelegramBot bot;
    private final PollingUpdateProvider owner;

    static {
        // todo provide a valid event for updates
        EVENT_FUNCTIONS.put(MessageUpdate.class, (update) -> null);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            TelegramRequest request = GetUpdates.builder()
                    .allowedUpdates(owner.getAllowedUpdates())
                    .offset(offset)
                    .timeout(owner.getTimeout())
                    .callback(this::handleUpdates)
                    .build();

            try {
                Response response = bot.getRegistry().getClient().newCall(request.build(bot).build()).execute();

                request.handleResponse(response);
            } catch (IOException e) {
                request.handleException(e);
            }

            try {
                Thread.sleep(owner.getSleepInterval());
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    public void handleUpdates(Update[] updates) {
        for (Update update : updates) {
            if (EVENT_FUNCTIONS.containsKey(update.getClass())) {
                // todo actually call the event
                EVENT_FUNCTIONS.get(update.getClass()).apply(update);
            }
        }

        offset = Stream.of(updates).mapToInt(Update::getUpdateId).max().orElse(offset);
    }
}
