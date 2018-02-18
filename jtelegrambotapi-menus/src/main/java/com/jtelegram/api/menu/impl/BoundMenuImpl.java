package com.jtelegram.api.menu.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.menu.BoundMenu;
import com.jtelegram.api.menu.MenuContext;
import com.jtelegram.api.menu.MenuScreen;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import javax.annotation.Nonnull;

public class BoundMenuImpl implements BoundMenu {

    private final MenuImpl menu;
    private final TextMessage message;
    private final MenuContext context;
    private MenuScreenImpl currentScreen;

    BoundMenuImpl(MenuImpl menu, TextMessage message) {
        this.menu = menu;
        this.message = message;
        this.context = new MenuContext();
        this.menu.getMenuConsumers().forEach(consumer -> {
            try {
                consumer.accept(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Nonnull
    public TelegramBot getBot() {
        return this.menu.getBot();
    }

    @Nonnull
    @Override
    public MenuImpl getMenu() {
        return this.menu;
    }

    @Nonnull
    @Override
    public MenuScreenImpl getScreen() {
        return this.currentScreen;
    }

    @Override
    public void setScreen(@Nonnull MenuScreen screen) {
        this.currentScreen = (MenuScreenImpl) screen;
        this.update();
    }

    @Nonnull
    @Override
    public TextMessage getMessage() {
        return this.message;
    }

    @Nonnull
    @Override
    public MenuContext getContext() {
        return this.context;
    }

    @Override
    public void update() {
        if (message.getSender().getId() != menu.getBot().getBotInfo().getId()) {
            String senderName = message.getSender().isBot()
                    ? message.getSender().getUsername()
                    : "a human";
            throw new IllegalArgumentException("Message was not sent by me! (sent by " + senderName + ")");
        }
        MenuScreenImpl screen = currentScreen;
        menu.getBot().perform(EditTextMessage.builder()
                .chatId(ChatId.of(message))
                .messageId(message.getMessageId())
                .text(screen.getText())
                .parseMode(screen.getParseMode())
                .replyMarkup(screen.getGrid().toReplyMarkup(screen.getUniqueId()))
                .build());
    }

}
