package io.jtelegram.api.chat.id;

import io.jtelegram.api.chat.Chat;

public interface ChatId<T> {
    T getId();

    static LongChatId of(long id) {
        return new LongChatId(id);
    }

    static StringChatId of(String id) {
        return new StringChatId(id);
    }

    static LongChatId of(Chat chat) {
        return new LongChatId(chat.getId());
    }
}
