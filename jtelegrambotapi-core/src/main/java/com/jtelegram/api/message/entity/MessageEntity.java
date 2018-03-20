package com.jtelegram.api.message.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MessageEntity {
    private MessageEntityType type;
    private int offset;
    private int length;
    private String content;

    public void updateContent(String text) {
        this.content = text.substring(offset, offset + length);
    }

    static class DefaultMessageEntity extends MessageEntity {}
    public static class Deserializer implements JsonDeserializer<MessageEntity> {

        @Override
        public MessageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            MessageEntityType type = context.deserialize(object.get("type"), MessageEntityType.class);
            if (type == null) {
                throw new UnsupportedOperationException("This version of jTelegramBotAPI does not support '" + object.get("type").getAsString() + "' message entities.");
            }
            return context.deserialize(object, type.getImplementationClass());
        }

    }

}
