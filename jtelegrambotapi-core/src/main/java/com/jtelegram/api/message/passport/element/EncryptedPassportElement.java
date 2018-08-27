package com.jtelegram.api.message.passport.element;

import com.google.gson.*;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@ToString
public class EncryptedPassportElement {
    private EncryptedPassportElementType type;
    private String data;

    public static class Deserializer implements JsonDeserializer<EncryptedPassportElement> {
        @Override
        public EncryptedPassportElement deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            EncryptedPassportElementType elementType = null;

            if (!object.has("type")) {
                return null;
            }

            try {
                elementType = EncryptedPassportElementType.valueOf(object.get("type").getAsString());
            } catch (IllegalArgumentException ignored) {}

            if (elementType == null) {
                return null;
            }

            return context.deserialize(element, elementType.getImplementingClass());
        }
    }
}
