package io.jtelegram.api.message.requests.helpers.input.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.lang.reflect.Type;

public class InputFileSerializer implements JsonSerializer<InputFile> {
    @Override
    public JsonElement serialize(InputFile inputFile, Type type, JsonSerializationContext context) {
        if (inputFile instanceof LocalInputFile) {
            return new JsonPrimitive("attach://" + ((File) inputFile.getData()).getName());
        }

        return new JsonPrimitive(inputFile.getData().toString());
    }
}
