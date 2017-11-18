package io.jtelegram.api.message;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageDeserializer implements JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        for (MessageType messageType : MessageType.values()) {
            if (object.has(messageType.name().toLowerCase())) {
                return context.deserialize(object, messageType.getCorrespondingClass());
            }
        }

        return null;
    }
}
