package io.jtelegram.api.message.impl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.entity.MessageEntity;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class TextMessage extends Message<String> {
    private String text;
    private List<MessageEntity> entities = new ArrayList<>();

    @Override
    public String getContent() {
        return text;
    }

    /**
     * Updates the entities to get the substring of
     * the message for ease of use for the developer.
     */
    public static class GsonAdapterFactory implements TypeAdapterFactory {
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
}
