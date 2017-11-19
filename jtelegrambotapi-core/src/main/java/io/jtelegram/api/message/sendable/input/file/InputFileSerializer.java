package io.jtelegram.api.message.sendable.input.file;

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
            // this is so our InputFileMessageRequest processor can realize
            // that this is a local file
            return new JsonPrimitive("file://" + ((File) inputFile.getData()).getAbsolutePath());
        }

        return new JsonPrimitive(inputFile.getData().toString());
    }
}
