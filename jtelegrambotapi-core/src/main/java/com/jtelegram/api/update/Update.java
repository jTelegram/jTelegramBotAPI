package com.jtelegram.api.update;

import com.google.gson.*;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.inline.InlineQuery;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.payments.PreCheckoutQuery;
import com.jtelegram.api.message.payments.ShippingQuery;
import javax.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public abstract class Update<T extends UpdateContents> {
    private int updateId;

    @Nonnull
    public abstract T getContents();

    @Getter
    @ToString(callSuper = true)
    public static class ChannelPostUpdate extends Update<Message> {
        @NonNull
        private Message channelPost;

        @Nonnull
        @Override
        public Message getContents() {
            return channelPost;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class ChosenInlineResultUpdate extends Update<ChosenInlineResult> {
        @NonNull
        private ChosenInlineResult chosenInlineResult;

        @Nonnull
        @Override
        public ChosenInlineResult getContents() {
            return chosenInlineResult;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedChannelPostUpdate extends Update<Message> {
        @NonNull
        private Message editedChannelPost;

        @Nonnull
        @Override
        public Message getContents() {
            return editedChannelPost;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedMessageUpdate extends Update<Message> {
        @NonNull
        private Message editedMessage;

        @Nonnull
        @Override
        public Message getContents() {
            return editedMessage;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class InlineQueryUpdate extends Update<InlineQuery> {
        @NonNull
        private InlineQuery inlineQuery;

        @Nonnull
        @Override
        public InlineQuery getContents() {
            return inlineQuery;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class CallbackQueryUpdate extends Update<CallbackQuery> {
        @NonNull
        private CallbackQuery callbackQuery;

        @Nonnull
        @Override
        public CallbackQuery getContents() {
            return callbackQuery;
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class MessageUpdate extends Update<Message> {
        @NonNull
        private Message message;

        @Nonnull
        @Override
        public Message getContents() {
            return message;
        }
    }

    @Getter
    public static class PreCheckoutQueryUpdate extends Update<PreCheckoutQuery> {
        @NonNull
        private PreCheckoutQuery preCheckoutQuery;

        @Nonnull
        @Override
        public PreCheckoutQuery getContents() {
            return preCheckoutQuery;
        }
    }

    @Getter
    public static class ShippingQueryUpdate extends Update<ShippingQuery> {
        @NonNull
        private ShippingQuery shippingQuery;

        @Nonnull
        @Override
        public ShippingQuery getContents() {
            return shippingQuery;
        }
    }

    public static class Deserializer implements JsonDeserializer<Update> {
        private static final Map<String, Class<? extends Update>> CLASS_MAP = new HashMap<>();

        static {
            for (UpdateType<? extends Update> type : UpdateType.ALL) {
                CLASS_MAP.put(type.getName().toLowerCase(), type.getUpdateClass());
            }
        }

        @Override
        public Update deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = jsonElement.getAsJsonObject();
            for (String key : object.keySet()) {
                if (CLASS_MAP.containsKey(key)) {
                    return context.deserialize(object, CLASS_MAP.get(key));
                }
            }

            return null;
        }
    }
}
