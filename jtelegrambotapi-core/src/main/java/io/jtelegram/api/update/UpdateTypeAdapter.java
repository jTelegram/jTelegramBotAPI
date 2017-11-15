package io.jtelegram.api.update;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Merely just lower case the enum's value
 */
public class UpdateTypeAdapter extends TypeAdapter<UpdateType> {
    @Override
    public void write(JsonWriter jsonWriter, UpdateType updateType) throws IOException {
        jsonWriter.value(updateType.name().toLowerCase());
    }

    @Override
    public UpdateType read(JsonReader jsonReader) throws IOException {
        return UpdateType.valueOf(jsonReader.nextString().toUpperCase());
    }
}
