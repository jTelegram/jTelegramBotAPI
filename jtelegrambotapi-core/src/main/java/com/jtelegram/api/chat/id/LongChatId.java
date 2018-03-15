package com.jtelegram.api.chat.id;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(of = "chatId")
@RequiredArgsConstructor
public class LongChatId implements ChatId<Long> {
    private final long chatId;

    @Override
    public Long getId() {
        return chatId;
    }

    @Override
    public String toString() {
        return String.valueOf(chatId);
    }
}
