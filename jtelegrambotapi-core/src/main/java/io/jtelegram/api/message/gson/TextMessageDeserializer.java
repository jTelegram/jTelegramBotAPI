package io.jtelegram.api.message.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.jtelegram.api.message.impl.TextMessage;

import java.lang.reflect.Type;

/**
 * Updates the entities to get the substring of
 * the message for ease of use for the developer.
 */
public class TextMessageDeserializer implements JsonDeserializer<TextMessage> {
    @Override
    public TextMessage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        TextMessage message = context.deserialize(jsonElement, TextMessage.class);
        message.getEntities().forEach((entity) -> entity.updateContent(message));
        return message;
    }
}
