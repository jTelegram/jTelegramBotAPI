package com.jtelegram.api.update;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.Event;
import com.jtelegram.api.events.channel.ChannelPostEditEvent;
import com.jtelegram.api.events.channel.ChannelPostEvent;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.events.location.LocationUpdateEvent;
import com.jtelegram.api.events.message.MessageEvent;
import com.jtelegram.api.events.message.edit.CaptionEditEvent;
import com.jtelegram.api.events.message.edit.TextMessageEditEvent;
import com.jtelegram.api.events.payment.PreCheckoutQueryEvent;
import com.jtelegram.api.events.payment.ShippingQueryEvent;
import com.jtelegram.api.message.CaptionableMessage;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.MessageType;
import com.jtelegram.api.message.impl.LocationMessage;
import com.jtelegram.api.message.impl.TextMessage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiFunction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateType<T extends Update> {
    public static final UpdateType<Update.ChannelPostUpdate> CHANNEL_POST = new UpdateType<>(
            "CHANNEL_POST",
            Update.ChannelPostUpdate.class,
            ChannelPostEvent::new
    );

    public static final UpdateType<Update.EditedChannelPostUpdate> EDITED_CHANNEL_POST = new UpdateType<>(
            "EDITED_CHANNEL_POST",
            Update.EditedChannelPostUpdate.class,
            ChannelPostEditEvent::new
    );

    public static final UpdateType<Update.InlineQueryUpdate> INLINE_QUERY = new UpdateType<>(
            "INLINE_QUERY",
            Update.InlineQueryUpdate.class,
            InlineQueryEvent::new
    );

    public static final UpdateType<Update.ChosenInlineResultUpdate> CHOSEN_INLINE_RESULT = new UpdateType<>(
            "CHOSEN_INLINE_RESULT",
            Update.ChosenInlineResultUpdate.class,
            ChosenInlineResultEvent::new
    );

    public static final UpdateType<Update.ShippingQueryUpdate> SHIPPING_QUERY = new UpdateType<>(
            "SHIPPING_QUERY",
            Update.ShippingQueryUpdate.class,
            ShippingQueryEvent::new
    );

    public static final UpdateType<Update.PreCheckoutQueryUpdate> PRE_CHECKOUT_QUERY = new UpdateType<>(
            "PRE_CHECKOUT_QUERY",
            Update.PreCheckoutQueryUpdate.class,
            PreCheckoutQueryEvent::new
    );

    public static final UpdateType<Update.CallbackQueryUpdate> CALLBACK_QUERY = new UpdateType<>(
            "CALLBACK_QUERY",
            Update.CallbackQueryUpdate.class,
            CallbackQueryEvent::new
    );

    public static final UpdateType<Update.MessageUpdate> MESSAGE = new UpdateType<>(
            "MESSAGE",
            Update.MessageUpdate.class,
            (bot, update) -> {
                MessageType<?, ?, ?> type = MessageType.typeFrom(update.getMessage());
                Class<? extends MessageEvent> eventClass = type.getReceiveEventClass();
                Constructor<? extends MessageEvent> constructor;

                try {
                    constructor = eventClass.getDeclaredConstructor(TelegramBot.class, Update.MessageUpdate.class, type.getMessageClass());
                } catch (NoSuchMethodException ex) {
                    System.out.println("INTERNAL ERROR: Cannot find appropriate event constructor for " + eventClass.getName());
                    return null;
                }

                MessageEvent event;

                try {
                    event = constructor.newInstance(bot, update, update.getMessage());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    System.out.println("There was an error creating a new instance of " + eventClass.getSimpleName() + "!");
                    ex.printStackTrace();
                    return null;
                }

                return event;
            }
    );

    public static final UpdateType<Update.EditedMessageUpdate> EDITED_MESSAGE = new UpdateType<>(
            "EDITED_MESSAGE",
            Update.EditedMessageUpdate.class,
            (bot, update) -> {
                Message updatedMessage = update.getEditedMessage();

                if (updatedMessage instanceof TextMessage) {
                    return new TextMessageEditEvent(bot, update, (TextMessage) updatedMessage);
                } else if (updatedMessage instanceof LocationMessage) {
                    return new LocationUpdateEvent(bot, update, (LocationMessage) updatedMessage);
                } else if (updatedMessage instanceof CaptionableMessage) {
                    return new CaptionEditEvent(bot, update, (CaptionableMessage) updatedMessage);
                }

                return null;
            }
    );

    public static final UpdateType<?>[] ALL = new UpdateType<?>[] { MESSAGE, EDITED_MESSAGE, CALLBACK_QUERY, CHANNEL_POST, EDITED_CHANNEL_POST, INLINE_QUERY, CHOSEN_INLINE_RESULT, SHIPPING_QUERY, PRE_CHECKOUT_QUERY };

    private String name;
    private Class<T> updateClass;
    private BiFunction<TelegramBot, T, Event> eventFunction;

    public static UpdateType<? extends Update> from(Class<? extends Update> clazz) {
        for (UpdateType<? extends Update> type : ALL) {
            if (type.updateClass.equals(clazz))
                return type;
        }

        return null;
    }

    public static UpdateType<? extends Update> from(String name) {
        for (UpdateType<? extends Update> type : ALL) {
            if (type.name.equalsIgnoreCase(name))
                return type;
        }

        return null;
    }

    public static class GsonTypeAdapter extends TypeAdapter<UpdateType> {
        @Override
        public void write(JsonWriter jsonWriter, UpdateType updateType) throws IOException {
            jsonWriter.value(updateType.name.toLowerCase());
        }

        @Override
        public UpdateType read(JsonReader jsonReader) throws IOException {
            return UpdateType.from(jsonReader.nextString());
        }
    }
}
