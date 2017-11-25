package io.jtelegram.api.inline.keyboard;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InlineKeyboardMarkup {
    private List<InlineKeyboardRow> inlineKeyboard;
}
