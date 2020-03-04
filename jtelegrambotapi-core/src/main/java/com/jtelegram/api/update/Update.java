package com.jtelegram.api.update;

import com.google.gson.*;
import com.jtelegram.api.ex.InvalidResponseException;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.inline.InlineQuery;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.payments.PreCheckoutQuery;
import com.jtelegram.api.message.payments.ShippingQuery;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class Update {
    private int updateId;

    public static abstract class TimeSensitiveUpdate extends Update {
        /**
         * @return The relevant unix time when the update
         * should be compared to.
         */
        public abstract long getEventTime();

        protected long getDateFromMessage(Message message) {
            return message.getForwardSignature() != null ? message.getForwardDate() : message.getDate();
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class ChannelPostUpdate extends TimeSensitiveUpdate {
        private Message channelPost;

        @Override
        public long getEventTime() {
            return getDateFromMessage(channelPost);
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class ChosenInlineResultUpdate extends Update {
        private ChosenInlineResult chosenInlineResult;
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedChannelPostUpdate extends TimeSensitiveUpdate {
        private Message editedChannelPost;

        @Override
        public long getEventTime() {
            return editedChannelPost.getEditDate();
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedMessageUpdate extends TimeSensitiveUpdate {
        private Message editedMessage;

        @Override
        public long getEventTime() {
            return editedMessage.getEditDate();
        }
    }

    @Getter
    @ToString(callSuper = true)
    public static class InlineQueryUpdate extends Update {
        private InlineQuery inlineQuery;
    }

    @Getter
    @ToString(callSuper = true)
    public static class CallbackQueryUpdate extends Update {
        private CallbackQuery callbackQuery;
    }

    @Getter
    @ToString(callSuper = true)
    public static class MessageUpdate extends TimeSensitiveUpdate {
        private Message message;

        @Override
        public long getEventTime() {
            return getDateFromMessage(message);
        }
    }

    @Getter
    public static class PreCheckoutQueryUpdate extends Update {
        private PreCheckoutQuery preCheckoutQuery;
    }

    @Getter
    public static class ShippingQueryUpdate extends Update {
        private ShippingQuery shippingQuery;
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
            if (!jsonElement.isJsonObject()) {
                throw new InvalidResponseException (
                        "Update is not a JSON Object",
                        jsonElement.toString()
                );
            }

            JsonObject object = jsonElement.getAsJsonObject();

            for (String key : object.keySet()) {
                if (CLASS_MAP.containsKey(key)) {
                    return context.deserialize(object, CLASS_MAP.get(key));
                }
            }

            throw new InvalidResponseException (
                    "Unfamiliar update object, update the bot API?",
                    object.toString()
            );
        }
    }
}
