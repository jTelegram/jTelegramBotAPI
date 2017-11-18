package io.jtelegram.api.message.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.jtelegram.api.message.impl.TextMessage;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Updates the entities to get the substring of
 * the message for ease of use for the developer.
 */
public class TextMessageDeserializer implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {
                T obj = delegate.read(in);

                if (obj instanceof TextMessage) {
                    TextMessage message = (TextMessage) obj;

                    message.getEntities().forEach((entity) -> entity.updateContent(message));
                }

                return obj;
            }
        };
    }
}
