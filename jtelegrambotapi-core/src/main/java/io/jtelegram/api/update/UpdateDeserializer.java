package io.jtelegram.api.update;

import com.google.gson.*;
import io.jtelegram.api.update.types.ChannelPostUpdate;
import io.jtelegram.api.update.types.EditedChannelPostUpdate;
import io.jtelegram.api.update.types.EditedMessageUpdate;
import io.jtelegram.api.update.types.MessageUpdate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UpdateDeserializer implements JsonDeserializer<Update> {
    private static final Map<String, Class<? extends Update>> CLASS_MAP = new HashMap<>();

    static {
        CLASS_MAP.put("message", MessageUpdate.class);
        CLASS_MAP.put("edited_message", EditedMessageUpdate.class);
        CLASS_MAP.put("channel_post", ChannelPostUpdate.class);
        CLASS_MAP.put("edited_channel_post", EditedChannelPostUpdate.class);
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
