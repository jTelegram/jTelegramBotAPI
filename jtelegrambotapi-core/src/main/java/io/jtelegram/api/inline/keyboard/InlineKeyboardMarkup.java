package io.jtelegram.api.inline.keyboard;

import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InlineKeyboardMarkup implements ReplyMarkup {
    private List<InlineKeyboardRow> inlineKeyboard;
}
