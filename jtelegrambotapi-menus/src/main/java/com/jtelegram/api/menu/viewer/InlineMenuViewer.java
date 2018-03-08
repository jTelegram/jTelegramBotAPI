package com.jtelegram.api.menu.viewer;

import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuViewer;
import com.jtelegram.api.requests.message.edit.EditMessageReplyMarkup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InlineMenuViewer implements MenuViewer {
    private String inlineMessageId;

    @Builder
    public InlineMenuViewer(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
    }

    @Override
    public void sendMenu(Menu menu) {
        menu.getBot().perform(EditMessageReplyMarkup.builder()
                .inlineMessageId(inlineMessageId)
                .replyMarkup(menu.toKeyboard())
                .errorHandler(e -> menu.handleException(e))
                .build()
        );
    }

}
