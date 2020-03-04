package com.jtelegram.api.message.entity;

import com.google.gson.*;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.ex.InvalidResponseException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@ToString
@EqualsAndHashCode
public class MessageEntity {
    protected MessageEntityType type;
    protected int offset;
    protected int length;
    protected String content;

    public void updateContent(String text) {
        this.content = text.substring(offset, offset + length);
    }

    static class DefaultMessageEntity extends MessageEntity {}
    public static class Deserializer implements JsonDeserializer<MessageEntity> {

        @Override
        public MessageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) {
                throw new InvalidResponseException (
                        "Message Entity is not a JSON object",
                        json.toString()
                );
            }

            JsonObject object = json.getAsJsonObject();
            MessageEntityType type = context.deserialize(object.get("type"), MessageEntityType.class);

            if (type == null) {
                TelegramBotRegistry.getMinorGsonErrorHandler().accept (
                        new InvalidResponseException (
                                "Invalid Message Entity Type. Update the API?",
                                object.toString()
                        )
                );

                return null;
            }

            return context.deserialize(object, type.getImplementationClass());
        }

    }

}
