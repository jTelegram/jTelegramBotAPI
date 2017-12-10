package com.jtelegram.api.menu.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.menu.BoundMenu;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import javax.annotation.Nonnull;

public class BoundMenuImpl implements BoundMenu {

    private final MenuImpl menu;
    private final TextMessage message;

    BoundMenuImpl(MenuImpl menu, TextMessage message) {
        this.menu = menu;
        this.message = message;
    }

    @Nonnull
    @Override
    public MenuImpl getMenu() {
        return this.menu;
    }

    @Nonnull
    @Override
    public TextMessage getMessage() {
        return this.message;
    }

    @Override
    public void update(@Nonnull TelegramBot bot) {
        if (message.getSender().getId() != bot.getBotInfo().getId()) {
            String senderName = message.getSender().isBot()
                    ? message.getSender().getUsername()
                    : "a human";
            throw new IllegalArgumentException("Message was not sent by me! (sent by " + senderName + ")");
        }
        MenuStateImpl state = menu.getState();
        bot.perform(EditTextMessage.builder()
                .chatId(ChatId.of(message))
                .messageId(message.getMessageId())
                .text(state.getText())
                .parseMode(state.getParseMode())
                .replyMarkup(state.getGrid().toReplyMarkup(state.stateId))
                .build());
    }

}
