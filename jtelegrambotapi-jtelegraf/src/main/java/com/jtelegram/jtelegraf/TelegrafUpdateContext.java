package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class TelegrafUpdateContext<UpdateContext extends TelegrafUpdateContext<UpdateContext, U, T>, U extends UpdateContents, T extends Update<U>> {

    @Nonnull
    private final TelegramBot bot;

    @Nonnull
    private final T update;

    @Nonnull
    public U getContents() {
        return update.getContents();
    }

    @SuppressWarnings("unchecked")
    public Optional<UpdateContext> filter(Predicate<UpdateContext> predicate) {
        return predicate.test((UpdateContext) this) ? Optional.of((UpdateContext) this) : Optional.empty();
    }

    public void filter(Predicate<UpdateContext> predicate, Consumer<UpdateContext> consumer) {
        filter(predicate).ifPresent(consumer);
    }

}
