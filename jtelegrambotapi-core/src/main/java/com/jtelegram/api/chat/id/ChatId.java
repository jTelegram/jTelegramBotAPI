package com.jtelegram.api.chat.id;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jtelegram.api.chat.Chat;

import java.lang.reflect.Type;

public interface ChatId<T> {
    T getId();

    static LongChatId of(long id) {
        return new LongChatId(id);
    }

    static StringChatId of(String id) {
        return new StringChatId(id);
    }

    static LongChatId of(Chat chat) {
        return new LongChatId(chat.getId());
    }

    class Serializer implements JsonSerializer<ChatId> {
        @Override
        public JsonElement serialize(ChatId chatId, Type type, JsonSerializationContext jsonSerializationContext) {
            return chatId instanceof LongChatId ? new JsonPrimitive(((LongChatId) chatId).getId()) : new JsonPrimitive(chatId.getId().toString());
        }
    }
}
