package com.jtelegram.api.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * Merely just lower case the enum's value
 */
@RequiredArgsConstructor
public class LowercaseEnumAdapter<T extends Enum<T>> extends TypeAdapter<T> {
    private final Class<T> enumClass;

    @Override
    public void write(JsonWriter jsonWriter, T anEnum) throws IOException {
        jsonWriter.value(anEnum.name().toLowerCase());
    }

    @Override
    public T read(JsonReader jsonReader) throws IOException {
        return Enum.valueOf(enumClass, jsonReader.nextString().toUpperCase());
    }
}
