package io.jtelegram.api.message.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.jtelegram.api.message.sendable.SendableMessageRequest;

import java.lang.reflect.Type;

public class SendableMessageRequestSerializer implements JsonSerializer<SendableMessageRequest> {
    @Override
    public JsonElement serialize(SendableMessageRequest request, Type type, JsonSerializationContext context) {
        JsonObject element = context.serialize(request, type).getAsJsonObject();

        element.remove("chatId");
        element.add("chatId", context.serialize(request.getChatId().getId()));

        return element;
    }
}
