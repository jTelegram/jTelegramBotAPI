package com.jtelegram.api.events;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import javax.annotation.Nonnull;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
public abstract class UpdateEvent<T extends Update<?>> extends Event {

    @Nonnull
    private T update;

    protected UpdateEvent(@Nonnull TelegramBot bot, @Nonnull T update) {
        super(bot);
        this.update = update;
    }

}
