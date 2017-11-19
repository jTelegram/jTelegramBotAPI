package io.jtelegram.api.chat.id;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ChatIdSerializer implements JsonSerializer<ChatId> {
    @Override
    public JsonElement serialize(ChatId chatId, Type type, JsonSerializationContext jsonSerializationContext) {
        return chatId instanceof LongChatId ? new JsonPrimitive(((LongChatId) chatId).getId()) : new JsonPrimitive(chatId.getId().toString());
    }
}
