package com.jtelegram.api.message;

import com.google.gson.*;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.chat.Chat;
import com.jtelegram.api.ex.InvalidResponseException;
import com.jtelegram.api.requests.message.DeleteMessage;
import com.jtelegram.api.requests.message.ForwardMessage;
import com.jtelegram.api.requests.message.edit.EditMessageReplyMarkup;
import com.jtelegram.api.user.User;
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

    /**
     * Creates a request builder for editing the reply markup of this message.
     *
     * @return the request builder
     */
    public EditMessageReplyMarkup.EditMessageReplyMarkupBuilder toEditReplyMarkupRequest() {
        return EditMessageReplyMarkup.forMessage(this);
    }

    /**
     * Creates a request builder for forwarding this message to another chat.
     *
     * @return the request builder
     */
    public ForwardMessage.ForwardMessageBuilder toForwardRequest() {
        return ForwardMessage.forMessage(this);
    }

    /**
     * Creates a request builder for deleting this message.
     *
     * @return the request builder
     */
    public DeleteMessage.DeleteMessageBuilder toDeleteRequest() {
        return DeleteMessage.forMessage(this);
    }

    public static class Deserializer implements JsonDeserializer<Message> {
        @Override
        public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (!jsonElement.isJsonObject()) {
                throw new InvalidResponseException (
                        "Message is not JSON Object",
                        jsonElement.toString()
                );
            }

            JsonObject object = jsonElement.getAsJsonObject();

            for (MessageType messageType : MessageType.values()) {
                if (object.has(messageType.name().toLowerCase())) {
                    return context.deserialize(object, messageType.getMessageClass());
                }
            }

            TelegramBotRegistry.getMinorGsonErrorHandler().accept (
                    new InvalidResponseException (
                            "Unfamiliar Message object, update the bot API?",
                            object.toString()
                    )
            );
            return null;
        }
    }
}
