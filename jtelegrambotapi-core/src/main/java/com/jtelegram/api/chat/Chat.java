package com.jtelegram.api.chat;

import com.google.gson.*;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.chat.id.LongChatId;
import com.jtelegram.api.ex.InvalidResponseException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Chat {
    private long id;
    private ChatType type;
    private String username;
    private ChatPhoto photo;

    public LongChatId getChatId() {
        return ChatId.of(this);
    }

    public static class Deserializer implements JsonDeserializer<Chat> {
        @Override
        public Chat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (!jsonElement.isJsonObject()) {
                throw new InvalidResponseException (
                        "Chat JSON is not a JSON Object",
                        jsonElement.toString()
                );
            }

            JsonObject object = jsonElement.getAsJsonObject();

            if (!object.has("type")) {
                throw new InvalidResponseException (
                        "Chat JSON does not have member 'type'",
                        jsonElement.toString()
                );
            }

            String chatTypeName;

            try {
                chatTypeName = object.get("type").getAsString();
            } catch (ClassCastException ex) {
                throw new InvalidResponseException (
                        "Chat Type is not a String",
                        jsonElement.toString()
                );
            }

            ChatType chatType;

            try {
                chatType = ChatType.valueOf(chatTypeName.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new InvalidResponseException (
                        "There is no chat type by the name " + chatTypeName,
                        jsonElement.toString()
                );
            }

            return context.deserialize(jsonElement, chatType.getRepresentingClass());
        }
    }
}
