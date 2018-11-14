package com.jtelegram.api.message.media;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an immutable list of photo sizes.
 *
 * @author Nick Robson
 */
@Getter
@AllArgsConstructor
public class Photo extends AbstractList<PhotoSize> {

    @Nonnull
    private final List<PhotoSize> photoSizeList;

    @Override
    public PhotoSize get(int index) {
        return photoSizeList.get(index);
    }

    @Override
    public int size() {
        return photoSizeList.size();
    }

    public static class Deserializer implements JsonDeserializer<Photo> {
        @Override
        public Photo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            List<PhotoSize> photoSizeList = jsonDeserializationContext.deserialize(jsonElement, List.class);
            return new Photo(photoSizeList);
        }
    }
}
