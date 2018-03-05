package com.jtelegram.api.menu.viewer;

import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuViewer;
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
