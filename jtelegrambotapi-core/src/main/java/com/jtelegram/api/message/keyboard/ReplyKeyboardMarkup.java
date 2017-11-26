package com.jtelegram.api.message.keyboard;

import com.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
public class ReplyKeyboardMarkup implements ReplyMarkup {
    @Singular
    private List<ReplyKeyboardRow> rows;
    private Boolean resizeKeyboard;
    private Boolean oneTimeKeyboard;
    private Boolean selective;
}
