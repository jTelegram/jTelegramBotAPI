package io.jtelegram.api.chat.message;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.ChatType;

import java.lang.reflect.Type;

public class ChatDeserializer implements JsonDeserializer<Chat> {
    @Override
    public Chat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        String chatType = jsonElement.getAsJsonObject().get("type").getAsString().toUpperCase();
        return context.deserialize(jsonElement, ChatType.valueOf(chatType).getRepresentingClass());
    }
}
