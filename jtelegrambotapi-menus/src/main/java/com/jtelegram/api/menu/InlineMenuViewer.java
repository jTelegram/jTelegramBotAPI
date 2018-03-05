package com.jtelegram.api.menu;

import com.jtelegram.api.requests.message.edit.EditMessageReplyMarkup;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InlineMenuViewer implements MenuViewer {
    private String inlineMessageId;

    @Override
    public void sendMenu(Menu menu) {
        menu.getBot().perform(EditMessageReplyMarkup.builder()
                .inlineMessageId(inlineMessageId)
                .replyMarkup(menu.toKeyboard())
                .build()
        );
    }
}
