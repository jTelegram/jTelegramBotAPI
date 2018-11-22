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
public class MessageEntity<T extends MessageEntity<T>> {
    protected MessageEntityType<T> type;
    protected int offset;
    protected int length;
    protected String content;

    public void updateContent(String text) {
        this.content = text.substring(offset, offset + length);
    }

    public static final class DefaultMessageEntity extends MessageEntity<DefaultMessageEntity> {
        private DefaultMessageEntity() {}
    }

    public static class Deserializer implements JsonDeserializer<MessageEntity> {

        @Override
        public MessageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            MessageEntityType type = context.deserialize(object.get("type"), MessageEntityType.class);
            Class<? extends MessageEntity> implementationClass;

            if (type != null) {
                //noinspection unchecked
                implementationClass = type.getImplementationClass();
            } else {
                implementationClass = UnsupportedMessageEntity.class;
                System.err.println("UnsupportedMessageEntity found (type = '" + object.get("type").getAsString() + "'). Please report this to the jTelegram developers!");
            }

            return context.deserialize(object, implementationClass);
        }

    }

}
