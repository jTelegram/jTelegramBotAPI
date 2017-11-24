package io.jtelegram.api.update;

import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.events.location.LocationUpdateEvent;
import io.jtelegram.api.events.message.MessageEvent;
import io.jtelegram.api.events.message.edit.CaptionEditEvent;
import io.jtelegram.api.events.message.edit.TextMessageEditEvent;
import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.MessageType;
import io.jtelegram.api.message.impl.LocationMessage;
import io.jtelegram.api.message.impl.TextMessage;
import io.jtelegram.api.requests.GetUpdates;
import io.jtelegram.api.requests.framework.TelegramRequest;
import io.jtelegram.api.update.types.EditedMessageUpdate;
import io.jtelegram.api.update.types.MessageUpdate;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PollingUpdateRunnable implements Runnable {
    private static final Map<Class<? extends Update>, BiFunction<TelegramBot, Update, Event>> EVENT_FUNCTIONS = new HashMap<>();
    private int offset = 0;
    private final TelegramBot bot;
    private final PollingUpdateProvider owner;

    static {
        EVENT_FUNCTIONS.put(MessageUpdate.class, (bot, up) -> {
            MessageUpdate update = (MessageUpdate) up;
            MessageType type = MessageType.typeFrom(update.getMessage());
            Class<? extends MessageEvent> eventClass = type.getReceiveEventClass();
            Constructor<? extends MessageEvent> constructor;

            try {
                constructor = eventClass.getDeclaredConstructor(TelegramBot.class, type.getMessageClass());
            } catch (NoSuchMethodException ex) {
                System.out.println("INTERNAL ERROR: Cannot find appropriate event constructor for " + eventClass.getName());
                return null;
            }

            MessageEvent event;

            try {
                event = constructor.newInstance(bot, update.getMessage());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                System.out.println("There was an error creating a new instance of " + eventClass.getSimpleName() + "!");
                ex.printStackTrace();
                return null;
            }

            return event;
        });

        EVENT_FUNCTIONS.put(EditedMessageUpdate.class, (bot, up) -> {
            EditedMessageUpdate update = (EditedMessageUpdate) up;
            Message updatedMessage = update.getEditedMessage();

            if (updatedMessage instanceof TextMessage) {
                return new TextMessageEditEvent(bot, (TextMessage) updatedMessage);
            } else if (updatedMessage instanceof LocationMessage) {
                return new LocationUpdateEvent(bot, (LocationMessage) updatedMessage);
            } else if (updatedMessage instanceof CaptionableMessage) {
                return new CaptionEditEvent(bot, (CaptionableMessage) updatedMessage);
            }

            return null;
        });
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
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
                bot.getEventRegistry().dispatch(EVENT_FUNCTIONS.get(update.getClass()).apply(bot, update));
            }
        }

        offset = Stream.of(updates).mapToInt(Update::getUpdateId).max().orElse(offset - 1) + 1;
    }
}
