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
 * A wrapper around an update, for use with a {@link TelegrafBot}.
 *
 * @author Nick Robson
 *
 * @param <ContextType> the type of this context, essentially a "self-type", so that any filter operations can be
 *                       strongly typed according to the actual type of this update context
 * @param <UpdateType> the type of the update: e.g. a MessageUpdate
 * @param <ContentsType> the type of the update contents: e.g. a Message
 */
@Getter
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class TelegrafUpdateContext<ContextType extends TelegrafUpdateContext<ContextType, UpdateType, ContentsType>, UpdateType extends Update<ContentsType>, ContentsType extends UpdateContents> {

    @Nonnull
    private final TelegramBot bot;

    @Nonnull
    private final UpdateType update;

    @Nonnull
    public ContentsType getContents() {
        return update.getContents();
    }

    /**
     * Filters this context according to the given predicate. If the predicate returns true, the returned
     * {@link Optional} is non-empty. If false, an empty Optional is returned.
     *
     * @param predicate the predicate to test against.
     *
     * @return an optional, as described above
     */
    @SuppressWarnings("unchecked")
    public Optional<ContextType> filter(Predicate<ContextType> predicate) {
        return predicate.test((ContextType) this) ? Optional.of((ContextType) this) : Optional.empty();
    }

    /**
     * Filters this context according to the given predicate. If the predicate returns true, the consumer is called
     * with this context. If false, it is a no-op.
     *
     * @param predicate the predicate
     * @param consumer the update context consumer
     */
    public void filter(Predicate<ContextType> predicate, Consumer<ContextType> consumer) {
        filter(predicate).ifPresent(consumer);
    }

}
