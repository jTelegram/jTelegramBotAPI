package io.jtelegram.api.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.Getter;

import java.lang.reflect.Type;

@Getter
public class Chat {
    private long id;
    private ChatType type;
    private String username;
    private ChatPhoto photo;

    public static class Deserializer implements JsonDeserializer<Chat> {
        @Override
        public Chat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            String chatType = jsonElement.getAsJsonObject().get("type").getAsString().toUpperCase();
            return context.deserialize(jsonElement, ChatType.valueOf(chatType).getRepresentingClass());
        }
    }
}
