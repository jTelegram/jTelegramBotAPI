package com.jtelegram.api.inline.keyboard;

import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InlineKeyboardMarkup implements ReplyMarkup {
    @Singular("keyboard")
    private List<InlineKeyboardRow> inlineKeyboard;
}
