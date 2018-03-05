package com.jtelegram.api.menu.viewer;

import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuViewer;
import com.jtelegram.api.requests.message.edit.EditMessageReplyMarkup;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegularMenuViewer implements MenuViewer {
    private ChatId chatId;
    private int messageId;

    @Override
    public void sendMenu(Menu menu) {
        menu.getBot().perform(EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(menu.toKeyboard())
                .build());
    }
}
