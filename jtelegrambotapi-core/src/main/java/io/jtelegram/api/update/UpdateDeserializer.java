package io.jtelegram.api.update;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UpdateDeserializer implements JsonDeserializer<Update> {
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
