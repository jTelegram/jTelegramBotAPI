package com.jtelegram.api.message.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonAdapter(UnsupportedMessageEntity.Adapter.class)
public class UnsupportedMessageEntity extends MessageEntity<UnsupportedMessageEntity> {

    private Map<String, JsonElement> properties;

    public Map<String, JsonElement> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public static final class Adapter implements JsonDeserializer<UnsupportedMessageEntity>, JsonSerializer<UnsupportedMessageEntity> {

        @Override
        public UnsupportedMessageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            UnsupportedMessageEntity entity = new UnsupportedMessageEntity();
            entity.properties = new HashMap<>();
            obj.entrySet().forEach(e -> entity.properties.put(e.getKey(), e.getValue()));

            entity.type = MessageEntityType.UNSUPPORTED;
            entity.offset = entity.properties.get("offset").getAsInt();
            entity.length = entity.properties.get("length").getAsInt();
            return entity;
        }

        @Override
        public JsonElement serialize(UnsupportedMessageEntity src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            src.properties.forEach(obj::add);
            return obj;
        }
    }

}
