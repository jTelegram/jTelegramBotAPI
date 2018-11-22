package com.jtelegram.api.message.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Objects;
import lombok.Getter;

@Getter
public final class MessageEntityType<T extends MessageEntity> {
    
    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> MENTION
            = new MessageEntityType<>("mention", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> HASHTAG
            = new MessageEntityType<>("hashtag", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> BOT_COMMAND
            = new MessageEntityType<>("bot_command", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> URL
            = new MessageEntityType<>("url", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> EMAIL
            = new MessageEntityType<>("email", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> BOLD
            = new MessageEntityType<>("bold", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> ITALIC
            = new MessageEntityType<>("italic", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> CODE
            = new MessageEntityType<>("code", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> PRE
            = new MessageEntityType<>("pre", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> CASHTAG
            = new MessageEntityType<>("cashtag", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<MessageEntity.DefaultMessageEntity> PHONE_NUMBER
            = new MessageEntityType<>("phone_number", MessageEntity.DefaultMessageEntity.class);

    public static final MessageEntityType<TextLinkMessageEntity> TEXT_LINK
            = new MessageEntityType<>("text_link", TextLinkMessageEntity.class);

    public static final MessageEntityType<TextMentionMessageEntity> TEXT_MENTION
            = new MessageEntityType<>("text_mention", TextMentionMessageEntity.class);

    public static final MessageEntityType<UnsupportedMessageEntity> UNSUPPORTED
            = new MessageEntityType<>("@@unsupported_by_api@@", UnsupportedMessageEntity.class);

    public static final MessageEntityType[] ALL = { MENTION, HASHTAG, BOT_COMMAND, URL, EMAIL, BOLD, ITALIC, CODE, PRE, CASHTAG, PHONE_NUMBER, TEXT_LINK, TEXT_MENTION };

    private final String name;
    private final Class<T> implementationClass;

    private MessageEntityType(String name, Class<T> implementationClass) {
        this.name = name.toLowerCase();
        this.implementationClass = implementationClass;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof MessageEntityType)) {
            return false;
        }
        return Objects.equals(name, ((MessageEntityType) obj).name);
    }

    public static class Adapter implements JsonSerializer<MessageEntityType>, JsonDeserializer<MessageEntityType> {
        @Override
        public JsonElement serialize(MessageEntityType src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src.name);
        }

        @Override
        public MessageEntityType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String string = json.getAsString();
            for (MessageEntityType type : ALL) {
                if (type.name.equals(string)) {
                    return type;
                }
            }
            return null;
        }
    }
}
