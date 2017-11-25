package io.jtelegram.api.message;

import com.google.gson.*;
import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@ToString
public abstract class Message<T> {
    private int messageId;
    private User from;
    private long date;
    private Chat chat;
    private Message replyToMessage;
    private long editDate;
    /** CHANNEL FIELDS **/
    private String authorSignature;
    /** FORWARDING FIELDS **/
    private User forwardedFrom;
    private Chat forwardedFromChat;
    private int forwardedFromMessageId;
    private String forwardSignature;
    private long forwardDate;

    public abstract T getContent();

    public User getSender() {
        return from;
    }

    public static class Deserializer implements JsonDeserializer<Message> {
        @Override
        public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = jsonElement.getAsJsonObject();

            for (MessageType messageType : MessageType.values()) {
                if (object.has(messageType.name().toLowerCase())) {
                    return context.deserialize(object, messageType.getMessageClass());
                }
            }

            return null;
        }
    }
}
