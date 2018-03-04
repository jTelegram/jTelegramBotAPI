package com.jtelegram.api.menu.old.impl;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.menu.old.MenuScreen;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuScreenImpl implements MenuScreen {

    private final UUID uuid;
    private final Supplier<String> textSupplier;
    private final ParseMode parseMode;
    private final MenuGridImpl grid;

    MenuScreenImpl(@Nonnull TelegramBot bot, @Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        this(bot, null, textSupplier, parseMode);
    }

    MenuScreenImpl(@Nonnull TelegramBot bot, @Nullable UUID uniqueId, Supplier<String> textSupplier, ParseMode parseMode) {
        MenuHandler handler = MenuHandler.getFor(bot);
        if (uniqueId == null) {
            do {
                uniqueId = UUID.randomUUID();
            } while (handler.menuScreensByUniqueId.containsKey(uniqueId));
        }
        handler.menuScreensByUniqueId.put(uniqueId, this);

        this.uuid = uniqueId;
        this.textSupplier = textSupplier;
        this.parseMode = parseMode;
        this.grid = new MenuGridImpl();
    }

    @Nonnull
    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Nonnull
    @Override
    public String getText() {
        return Objects.requireNonNull(textSupplier.get(), "text must be non-null");
    }

    @Nullable
    @Override
    public ParseMode getParseMode() {
        return parseMode;
    }

    @Nonnull
    @Override
    public MenuGridImpl getGrid() {
        return grid;
    }
}
