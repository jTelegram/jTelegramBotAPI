package io.jtelegram.api.message.keyboard;

import com.google.gson.*;
import lombok.*;

import java.lang.reflect.Type;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyKeyboardRow {
    @Singular
    private List<KeyboardButton> buttons;

    public static class Serializer implements JsonSerializer<ReplyKeyboardRow> {
        @Override
        public JsonElement serialize(ReplyKeyboardRow replyKeyboardRow, Type type, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            replyKeyboardRow.buttons.forEach((e) -> array.add(context.serialize(e)));
            return array;
        }
    }
}
