package com.jtelegram.api.message.input.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jtelegram.api.util.MultipartBodyPublisher;

import java.lang.reflect.Type;

public interface InputFile<T> {
    T getData();

    /**
     * Identifier for the InputFile
     * If non-null, then this will
     * use the attach protocol and
     * call the attachTo method
     */
    String getIdentifier();

    default boolean isAttachable() {
        return getIdentifier() != null;
    }

    default void attachTo(MultipartBodyPublisher.Builder builder) {}

    class Serializer implements JsonSerializer<InputFile> {
        @Override
        public JsonElement serialize(InputFile inputFile, Type type, JsonSerializationContext context) {
            String identifier = inputFile.getIdentifier();

            if (identifier != null) {
                return new JsonPrimitive("attach://" + identifier);
            }

            return new JsonPrimitive(inputFile.getData().toString());
        }
    }
}
