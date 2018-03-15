package com.jtelegram.api.update;

import com.google.gson.*;
import com.jtelegram.api.inline.CallbackQuery;
import com.jtelegram.api.inline.InlineQuery;
import com.jtelegram.api.inline.result.ChosenInlineResult;
import com.jtelegram.api.message.Message;
import com.jtelegram.api.message.payments.PreCheckoutQuery;
import com.jtelegram.api.message.payments.ShippingQuery;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.ToString;

@Getter
@ToString
public class Update {
    private int updateId;

    @Getter
    @ToString(callSuper = true)
    public static class ChannelPostUpdate extends Update {
        private Message channelPost;
    }

    @Getter
    @ToString(callSuper = true)
    public static class ChosenInlineResultUpdate extends Update {
        private ChosenInlineResult chosenInlineResult;
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedChannelPostUpdate extends Update {
        private Message editedChannelPost;
    }

    @Getter
    @ToString(callSuper = true)
    public static class EditedMessageUpdate extends Update {
        private Message editedMessage;
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
    public static class MessageUpdate extends Update {
        private Message message;
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
