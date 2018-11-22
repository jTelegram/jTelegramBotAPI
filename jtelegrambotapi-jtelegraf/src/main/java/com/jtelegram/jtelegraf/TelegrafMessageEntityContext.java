package com.jtelegram.jtelegraf;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.update.Update;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
@ParametersAreNonnullByDefault
public class TelegrafMessageEntityContext<T extends MessageEntityType<E>, E extends MessageEntity<E>> extends TelegrafMessageContext<TelegrafMessageEntityContext<T, E>, String, TextMessage> {

    @Nonnull
    private final T entityType;

    @Nonnull
    private final List<E> entities;

    public TelegrafMessageEntityContext(TelegramBot bot, Update.MessageUpdate update, TextMessage message, T entityType, List<E> entities) {
        super(bot, update, message);
        this.entityType = entityType;
        this.entities = Collections.unmodifiableList(entities);
    }

    public TelegrafMessageEntityContext<T, E> withEntities(List<E> entities) {
        Objects.requireNonNull(entities, "entities cannot be null");
        return new TelegrafMessageEntityContext<>(this.getBot(), this.getUpdate(), this.getMessage(), entityType, entities);
    }

}
