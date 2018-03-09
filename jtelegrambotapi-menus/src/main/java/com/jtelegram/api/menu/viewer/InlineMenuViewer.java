package com.jtelegram.api.menu.viewer;

import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuViewer;
import com.jtelegram.api.requests.message.edit.EditMessageReplyMarkup;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import com.jtelegram.api.util.TextBuilder;
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
        TextBuilder tb = menu.getMenuMessage();
        if (tb == null) {
            menu.getBot().perform(EditMessageReplyMarkup.builder()
                    .inlineMessageId(inlineMessageId)
                    .replyMarkup(menu.toKeyboard())
                    .errorHandler(menu::handleException)
                    .build()
            );
        } else {
            menu.getBot().perform(EditTextMessage.builder()
                    .inlineMessageId(inlineMessageId)
                    .replyMarkup(menu.toKeyboard())
                    .text(tb)
                    .errorHandler(menu::handleException)
                    .build()
            );
        }

    }

}
