package io.jtelegram.api.message.keyboard;

import io.jtelegram.api.requests.message.framework.ReplyMarkup;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyKeyboardMarkup implements ReplyMarkup {
    @Singular
    private List<ReplyKeyboardRow> rows;
    private Boolean resizeKeyboard;
    private Boolean oneTimeKeyboard;
    private Boolean selective;
}
