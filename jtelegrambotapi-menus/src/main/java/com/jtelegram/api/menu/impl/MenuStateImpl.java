package com.jtelegram.api.menu.impl;

import com.jtelegram.api.menu.MenuState;
import com.jtelegram.api.requests.message.framework.ParseMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MenuStateImpl implements MenuState {

    private static final AtomicInteger stateIdCounter = new AtomicInteger();

    final int stateId;
    private final Supplier<String> textSupplier;
    private final ParseMode parseMode;
    private final MenuGridImpl grid;

    MenuStateImpl(@Nonnull Supplier<String> textSupplier, @Nullable ParseMode parseMode) {
        this.stateId = MenuStateImpl.stateIdCounter.getAndIncrement();
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
