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
 * A wrapper around a message entity, for use with a {@link TelegrafBot}.
 *
 * @author Nick Robson
 *
 * @param <EntityType> the entity type being listened for
 * @param <Entity> the type of the entity: e.g. a {@link com.jtelegram.api.message.entity.TextMentionMessageEntity}
 */
@Getter
@ParametersAreNonnullByDefault
public class TelegrafMessageEntityContext<EntityType extends MessageEntityType<Entity>, Entity extends MessageEntity<Entity>> extends TelegrafMessageContext<TelegrafMessageEntityContext<EntityType, Entity>, TextMessage, String> {

    @Nonnull
    private final EntityType entityType;

    @Nonnull
    private final List<Entity> entities;

    public TelegrafMessageEntityContext(TelegramBot bot, Update.MessageUpdate update, TextMessage message, EntityType entityType, List<Entity> entities) {
        super(bot, update, message);
        this.entityType = entityType;
        this.entities = Collections.unmodifiableList(entities);
    }

    /**
     * Returns a new message entity context with the given list of entities
     *
     * @param entities the new entities
     *
     * @return the new message entity context
     */
    public TelegrafMessageEntityContext<EntityType, Entity> withEntities(List<Entity> entities) {
        Objects.requireNonNull(entities, "entities cannot be null");
        return new TelegrafMessageEntityContext<>(this.getBot(), this.getUpdate(), this.getMessage(), entityType, entities);
    }

}
