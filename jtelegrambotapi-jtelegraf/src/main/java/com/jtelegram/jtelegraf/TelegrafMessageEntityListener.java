package com.jtelegram.jtelegraf;

import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;

/**
 * A listener for a {@link TelegrafMessageEntityContext}, to be called when such a context is received.
 *
 * @author Nick Robson
 *
 * @param <EntityType> the entity type being listened for
 * @param <Entity> the type of the entity: e.g. a {@link com.jtelegram.api.message.entity.TextMentionMessageEntity}
 */
public interface TelegrafMessageEntityListener<EntityType extends MessageEntityType<Entity>, Entity extends MessageEntity<Entity>> {

    /**
     * Called when a message entity is received corresponding to this listener
     *
     * @param context the context of the message entity
     */
    void onMessageEntity(TelegrafMessageEntityContext<EntityType, Entity> context);

}
