package io.jtelegram.api.update;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.events.Event;
import io.jtelegram.api.events.channel.ChannelPostEditEvent;
import io.jtelegram.api.events.channel.ChannelPostEvent;
import io.jtelegram.api.events.inline.ChosenInlineResultEvent;
import io.jtelegram.api.events.inline.InlineQueryEvent;
import io.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import io.jtelegram.api.events.location.LocationUpdateEvent;
import io.jtelegram.api.events.message.MessageEvent;
import io.jtelegram.api.events.message.edit.CaptionEditEvent;
import io.jtelegram.api.events.message.edit.TextMessageEditEvent;
import io.jtelegram.api.events.payment.PreCheckoutQueryEvent;
import io.jtelegram.api.events.payment.ShippingQueryEvent;
import io.jtelegram.api.message.CaptionableMessage;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.MessageType;
import io.jtelegram.api.message.impl.LocationMessage;
import io.jtelegram.api.message.impl.TextMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.function.BiFunction;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateType<T extends Update> {
    public static final UpdateType<Update.ChannelPostUpdate> CHANNEL_POST = new UpdateType<>(
            "CHANNEL_POST",
            Update.ChannelPostUpdate.class,
            (bot, update) -> new ChannelPostEvent(bot, update.getChannelPost())
    );

    public static final UpdateType<Update.EditedChannelPostUpdate> EDITED_CHANNEL_POST = new UpdateType<>(
            "EDITED_CHANNEL_POST",
            Update.EditedChannelPostUpdate.class,
            (bot, update) -> new ChannelPostEditEvent(bot, update.getEditedChannelPost())
    );

    public static final UpdateType<Update.InlineQueryUpdate> INLINE_QUERY = new UpdateType<>(
            "INLINE_QUERY",
            Update.InlineQueryUpdate.class,
            (bot, update) -> new InlineQueryEvent(bot, update.getInlineQuery())
    );

    public static final UpdateType<Update.ChosenInlineResultUpdate> CHOSEN_INLINE_RESULT = new UpdateType<>(
            "CHOSEN_INLINE_RESULT",
            Update.ChosenInlineResultUpdate.class,
            (bot, update) -> new ChosenInlineResultEvent(bot, update.getChosenInlineResult())
    );

    public static final UpdateType<Update.ShippingQueryUpdate> SHIPPING_QUERY = new UpdateType<>(
            "SHIPPING_QUERY",
            Update.ShippingQueryUpdate.class,
            (bot, update) -> new ShippingQueryEvent(bot, update.getShippingQuery())
    );

    public static final UpdateType<Update.PreCheckoutQueryUpdate> PRE_CHECKOUT_QUERY = new UpdateType<>(
            "PRE_CHECKOUT_QUERY",
            Update.PreCheckoutQueryUpdate.class,
            (bot, update) -> new PreCheckoutQueryEvent(bot, update.getPreCheckoutQuery())
    );

    public static final UpdateType<Update.CallbackQueryUpdate> CALLBACK_QUERY = new UpdateType<>(
            "CALLBACK_QUERY",
            Update.CallbackQueryUpdate.class,
            (bot, update) -> new CallbackQueryEvent(bot, update.getCallbackQuery())
    );

    public static final UpdateType<Update.MessageUpdate> MESSAGE = new UpdateType<>(
            "MESSAGE",
            Update.MessageUpdate.class,
            (bot, update) -> {
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
            }
    );

    public static final UpdateType<Update.EditedMessageUpdate> EDITED_MESSAGE = new UpdateType<>(
            "EDITED_MESSAGE",
            Update.EditedMessageUpdate.class,
            (bot, update) -> {
                Message updatedMessage = update.getEditedMessage();

                if (updatedMessage instanceof TextMessage) {
                    return new TextMessageEditEvent(bot, (TextMessage) updatedMessage);
                } else if (updatedMessage instanceof LocationMessage) {
                    return new LocationUpdateEvent(bot, (LocationMessage) updatedMessage);
                } else if (updatedMessage instanceof CaptionableMessage) {
                    return new CaptionEditEvent(bot, (CaptionableMessage) updatedMessage);
                }

                return null;
            }
    );

    public static final UpdateType<? extends Update>[] ALL = new UpdateType[] { MESSAGE, EDITED_MESSAGE, CALLBACK_QUERY, CHANNEL_POST, EDITED_CHANNEL_POST, INLINE_QUERY, CHOSEN_INLINE_RESULT, SHIPPING_QUERY, PRE_CHECKOUT_QUERY };

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

    public static class Serializer implements JsonSerializer<UpdateType> {
        @Override
        public JsonElement serialize(UpdateType updateType, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(updateType.name);
        }
    }
}
