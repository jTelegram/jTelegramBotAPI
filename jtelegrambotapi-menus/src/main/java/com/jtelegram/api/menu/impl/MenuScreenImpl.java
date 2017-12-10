package com.jtelegram.api.menu.impl;

import com.jtelegram.api.menu.MenuScreen;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuScreenImpl implements MenuScreen {

    private static final AtomicInteger screenIdCounter = new AtomicInteger();

    final int screenId;
    private final Supplier<String> textSupplier;
    private final ParseMode parseMode;
    private final MenuGridImpl grid;

    MenuScreenImpl(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        this.screenId = MenuScreenImpl.screenIdCounter.getAndIncrement();
        this.textSupplier = textSupplier;
        this.parseMode = parseMode;
        this.grid = new MenuGridImpl();
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
