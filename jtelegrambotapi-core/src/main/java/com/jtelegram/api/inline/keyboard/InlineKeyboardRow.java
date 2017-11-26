package com.jtelegram.api.inline.keyboard;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.lang.reflect.Type;
import java.util.List;

@Getter
@Builder
public class InlineKeyboardRow {
    @Singular
    private List<InlineKeyboardButton> buttons;

    public static class Serializer implements JsonSerializer<InlineKeyboardRow> {
        @Override
        public JsonElement serialize(InlineKeyboardRow row, Type type, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            row.buttons.forEach((e) -> array.add(context.serialize(e)));
            return array;
        }
    }
}
