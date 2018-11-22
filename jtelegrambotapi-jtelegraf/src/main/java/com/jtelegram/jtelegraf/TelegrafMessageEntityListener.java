package com.jtelegram.jtelegraf;

import com.jtelegram.api.message.entity.MessageEntity;
import com.jtelegram.api.message.entity.MessageEntityType;

/**
 * @author Nick Robson
 */
public interface TelegrafMessageEntityListener<T extends MessageEntityType<E>, E extends MessageEntity<E>> {

    void onMessageEntity(TelegrafMessageEntityContext<T, E> context);

}
